-- Insert sample subscribers
INSERT INTO subscriber (`name`, `createdAt`, `updatedAt`)
VALUES
('Alice', NOW(), NOW()),
('Bob', NOW(), NOW()),
('Charlie', NOW(), NOW());

-- Insert sample topics
INSERT INTO topic (`name`, `createdAt`, `updatedAt`)
VALUES
('news', NOW(), NOW()),
('sports', NOW(), NOW()),
('tech', NOW(), NOW());

INSERT INTO subscription (`subscriber_id`, `topic_id`)
VALUES
(1, 1), -- Alice → news
(1, 3), -- Alice → tech
(2, 2), -- Bob → sports
(3, 1), -- Charlie → news
(3, 2), -- Charlie → sports
(3, 3); -- Charlie → tech

-- Insert sample messages
INSERT INTO message (`subscriber_id`, `topic_id`, `payload`, `createdAt`, `updatedAt`)
VALUES
(1, 1, 'Breaking News!', NOW(), NOW()),         -- Alice receives news
(1, 3, 'New Tech Released!', NOW(), NOW()),     -- Alice receives tech
(2, 2, 'Sports Highlights', NOW(), NOW()),      -- Bob receives sports
(3, 1, 'World News', NOW(), NOW()),             -- Charlie receives news
(3, 2, 'Live Match', NOW(), NOW()),             -- Charlie receives sports
(3, 3, 'AI Trends', NOW(), NOW());              -- Charlie receives tech