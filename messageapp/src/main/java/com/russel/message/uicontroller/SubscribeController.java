package com.russel.message.uicontroller;

import com.russel.message.model.Message;
import com.russel.message.MessageAppService;
import com.russel.message.SubscriberClient;
import com.russel.message.model.Subscriber;
import com.russel.message.model.Topic;
import com.russel.message.uiservice.MessageAppServiceImpl;
import com.russel.message.utils.ConverterUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class SubscribeController implements SubscriberClient {
    @FXML private VBox rootVBox;
    @FXML private Label titleLabel;
    @FXML private ComboBox<Subscriber> nameComboBox;
    @FXML private ComboBox<Topic> topicComboBox;
    private MessageAppService messageAppService = new MessageAppServiceImpl();
    public String subscriberName;
    public Topic topic;
    private ChatController chatController;
    private Timer timer;
    //================================================================================//
    public void setChatController(ChatController controller) {
        this.chatController = controller;
    }
    //================================================================================//
    @FXML
    public void initialize() {
        rootVBox.setPadding(new Insets(20, 20, 20, 20));

        titleLabel.setStyle("-fx-font-size: 24px; -fx-text-fill: #444444; -fx-font-weight: bold;");

        nameComboBox.setEditable(true);
        topicComboBox.setEditable(true);

        List<Subscriber> existingSubscribers = messageAppService.getAllSubscribers();
        List<Topic> existingTopics = messageAppService.getAllTopics();

        nameComboBox.getItems().addAll(existingSubscribers);
        topicComboBox.getItems().addAll(existingTopics);

        nameComboBox.setConverter(ConverterUtils.subscriberNameConverter(existingSubscribers));
        topicComboBox.setConverter(ConverterUtils.topicNameConverter(existingTopics));
    }
    //================================================================================//
    @FXML
    private void handleSubscribe() {
        String nameText = nameComboBox.getEditor().getText().trim();
        String topicText = topicComboBox.getEditor().getText().trim();

        if (nameText.isEmpty() || topicText.isEmpty()) {
            String msg = (nameText.isEmpty() && topicText.isEmpty()) ? "Name and topic cannot be empty." :
                    nameText.isEmpty() ? "Name cannot be empty." : "Topic cannot be empty.";
            new Alert(Alert.AlertType.ERROR, msg).showAndWait();
            return;
        }

        Subscriber subscriber = nameComboBox.getConverter().fromString(nameText);
        Topic selectedTopic = topicComboBox.getConverter().fromString(topicText);

        nameComboBox.setValue(subscriber);
        topicComboBox.setValue(selectedTopic);

        this.subscriberName = subscriber.getName();
        this.topic = selectedTopic;

        messageAppService.subscribe(topic, this);

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/Chat.fxml"));
            Parent root = loader.load();

            ChatController controller = loader.getController();
            controller.init(this, topic, messageAppService);
            setChatController(controller);

            Stage stage = (Stage) nameComboBox.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.setWidth(400);
            stage.setHeight(700);
            stage.setTitle("Chat - " + subscriberName + " (" + topic.getName() + ")");
            stage.show();

            pollMessages();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
    //================================================================================//
    @Override
    public void receive(List<Message> messages) {
        if (chatController != null) {
            javafx.application.Platform.runLater(() -> {
                chatController.displayMessages(messages);
            });
        }
    }
    //================================================================================//
    @Override
    public String getName() {
        return subscriberName;
    }
    //================================================================================//
    private void pollMessages() {
        if (timer != null) {
            timer.cancel();
        }

        timer = new Timer(true);

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                List<Message> messages = messageAppService.getMessages(subscriberName, topic.getName());
                receive(messages);
            }
        }, 0, 3000);
    }
}
