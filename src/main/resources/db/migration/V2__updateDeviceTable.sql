ALTER TABLE device
ADD status ENUM('inactive', 'active') DEFAULT 'inactive';

ALTER TABLE device
ADD UNIQUE (name);