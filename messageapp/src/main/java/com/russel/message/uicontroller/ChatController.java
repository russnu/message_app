package com.russel.message.uicontroller;
import com.russel.message.model.Message;
import com.russel.message.SubscriberClient;
import com.russel.message.model.Topic;
import javafx.fxml.FXML;
import com.russel.message.MessageAppService;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.util.List;

public class ChatController {
    @FXML private VBox rootVBox;
    @FXML private TextFlow topicTextFlow;
    @FXML private ListView<String> messageList;
    @FXML private TextField messageField;

    private MessageAppService messageAppService;
    private SubscriberClient subscriberClient;
    private Topic topic;

    public void init(SubscriberClient subscriberClient, Topic topic, MessageAppService messageAppService) {
        this.subscriberClient = subscriberClient;
        this.topic = topic;
        this.messageAppService = messageAppService;

        rootVBox.setPadding(new Insets(20, 20, 20, 20));


        Text label = new Text("Topic: ");
        label.setStyle("-fx-fill: #444444; -fx-font-size: 16px;");

        Text name = new Text(topic.getName());
        name.setStyle("-fx-font-weight: bold; -fx-font-style: italic; -fx-fill: #222222; -fx-font-size: 24px;");

        topicTextFlow.getChildren().setAll(label, name);


        List<Message> messages = messageAppService.getMessages(subscriberClient.getName(), topic.getName());
        displayMessages(messages);

        messageField.setOnAction(e -> handleSend());
    }

    @FXML
    private void handleSend() {
        String payload = messageField.getText().trim();
        if (payload.isEmpty()) return;
        Message message = new Message(topic, payload);
        messageAppService.publish(message);
        messageField.clear();
        List<Message> messages = messageAppService.getMessages(subscriberClient.getName(), topic.getName());
        displayMessages(messages);
    }

    public void displayMessages(List<Message> messages) {
        messageList.getItems().clear();
        for (Message msg : messages) {
            messageList.getItems().add(msg.getPayload());
        }

        messageList.setCellFactory(listView -> new ListCell<String>() {
            private final Text text = new Text();
            private final VBox container = new VBox(text); // Wrap in VBox

            {
                text.wrappingWidthProperty().bind(listView.widthProperty().subtract(30));
                container.setPadding(new Insets(10,5, 10, 5));
            }

            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    text.setText(item);
                    setGraphic(container);
                }
            }
        });
    }
}
