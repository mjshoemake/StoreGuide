CREATE TABLE `storeguide`.`user` (
  `user_pk` INT NOT NULL AUTO_INCREMENT,
  `fname` VARCHAR(25) NOT NULL,
  `lname` VARCHAR(25) NOT NULL,
  `email` VARCHAR(150) NOT NULL,
  `city` VARCHAR(70) NOT NULL,
  `state` CHAR(2) NOT NULL,
  `searchable` VARCHAR(145) NOT NULL,
  `username` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`user_pk`),
  UNIQUE INDEX `iduser_UNIQUE` (`user_pk` ASC),
  UNIQUE INDEX `searchable_UNIQUE` (`searchable` ASC));
  
CREATE TABLE `storeguide`.`role` (
  `role_pk` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `active` CHAR(1) NOT NULL,
  PRIMARY KEY (`role_pk`),
  UNIQUE INDEX `role_pk_UNIQUE` (`role_pk` ASC),
  INDEX `role_name` (`name` ASC));
  
  CREATE TABLE `storeguide`.`feature` (
  `feature_pk` INT NOT NULL AUTO_INCREMENT,
  `role_pk` INT NOT NULL,
  `name` VARCHAR(100) NOT NULL,
  `active` CHAR(1) NOT NULL,
  PRIMARY KEY (`feature_pk`),
  UNIQUE INDEX `feature_pk_UNIQUE` (`feature_pk` ASC),
  INDEX `fkRole_idx` (`role_pk` ASC),
  INDEX `idxName` (`name` ASC),
  CONSTRAINT `fkRole`
    FOREIGN KEY (`role_pk`)
    REFERENCES `storeguide`.`role` (`role_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
    
CREATE TABLE `storeguide`.`franchise` (
  `franchise_pk` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(150) NOT NULL,
  `website` VARCHAR(300) NULL,
  `company_email` VARCHAR(150) NULL,
  PRIMARY KEY (`franchise_pk`),
  UNIQUE INDEX `franchise_pk_UNIQUE` (`franchise_pk` ASC),
  INDEX `idxName` (`name` ASC));
  
CREATE TABLE `storeguide`.`store` (
  `store_pk` INT NOT NULL AUTO_INCREMENT,
  `franchise_pk` INT NULL,
  `name` VARCHAR(150) NOT NULL,
  `addr1` VARCHAR(60) NOT NULL,
  `addr2` VARCHAR(60) NULL,
  `addr3` VARCHAR(60) NULL,
  `addr4` VARCHAR(60) NULL,
  `city` VARCHAR(70) NULL,
  `state` CHAR(2) NULL,
  `zip` VARCHAR(10) NULL,
  `website` VARCHAR(200) NULL,
  `store_email` VARCHAR(150) NULL,
  `searchable` VARCHAR(200) NULL,
  PRIMARY KEY (`store_pk`),
  UNIQUE INDEX `store_pk_UNIQUE` (`store_pk` ASC),
  INDEX `fkFranchise_idx` (`franchise_pk` ASC),
  INDEX `idxSearchable` (`searchable` ASC),
  CONSTRAINT `fkFranchise`
    FOREIGN KEY (`franchise_pk`)
    REFERENCES `storeguide`.`franchise` (`franchise_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);  
    
CREATE TABLE `storeguide`.`item_category` (
  `item_category_pk` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(30) NOT NULL,
  `description` VARCHAR(150) NULL,
  `parent_pk` INT NULL,
  PRIMARY KEY (`item_category_pk`),
  UNIQUE INDEX `item_category_pk_UNIQUE` (`item_category_pk` ASC));

CREATE TABLE `storeguide`.`global_item_type` (
  `item_type_pk` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(80) NOT NULL,
  `description` VARCHAR(150) NULL,
  PRIMARY KEY (`item_type_pk`),
  UNIQUE INDEX `item_type_pk_UNIQUE` (`item_type_pk` ASC),
  INDEX `idxName` (`name` ASC));

CREATE TABLE `storeguide`.`store_section` (
  `store_section_pk` INT NOT NULL AUTO_INCREMENT,
  `store_pk` INT NULL,
  `aisle_y_n` CHAR(1) NOT NULL,
  `display_order` INT NOT NULL,
  `natural_order` INT NOT NULL,
  `name` VARCHAR(30) NOT NULL,
  `description` VARCHAR(150) NULL,
  PRIMARY KEY (`store_section_pk`),
  UNIQUE INDEX `store_section_pk_UNIQUE` (`store_section_pk` ASC),
  INDEX `fkStore_idx` (`store_pk` ASC),
  INDEX `idxName` (`name` ASC),
  CONSTRAINT `fkStore`
    FOREIGN KEY (`store_pk`)
    REFERENCES `storeguide`.`store` (`store_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);  
    
ALTER TABLE `storeguide`.`store_section` 
ADD UNIQUE INDEX `display_order_UNIQUE` (`display_order` ASC),
ADD UNIQUE INDEX `natural_order_UNIQUE` (`natural_order` ASC);

CREATE TABLE `storeguide`.`store_item` (
  `store_item_pk` INT NOT NULL AUTO_INCREMENT,
  `store_pk` INT NOT NULL,
  `item_type_pk` INT NOT NULL,
  `user_pk` INT NULL,
  `store_section_pk` INT NOT NULL,
  `description` VARCHAR(150) NULL,
  PRIMARY KEY (`store_item_pk`),
  UNIQUE INDEX `store_item_pk_UNIQUE` (`store_item_pk` ASC),
  INDEX `fkStoreItemToStore_idx` (`store_pk` ASC),
  CONSTRAINT `fkStore`
    FOREIGN KEY (`store_pk`)
    REFERENCES `storeguide`.`store` (`store_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);  
    
CREATE TABLE `storeguide`.`store_item` (
  `store_item_pk` INT NOT NULL AUTO_INCREMENT,
  `store_pk` INT NOT NULL,
  `item_type_pk` INT NOT NULL,
  `store_section_pk` INT NOT NULL,
  `description` VARCHAR(150) NULL,
  PRIMARY KEY (`store_item_pk`),
  UNIQUE INDEX `store_item_pk_UNIQUE` (`store_item_pk` ASC),
  INDEX `fkStoreItemToStore_idx` (`store_pk` ASC),
  INDEX `fkStoreItemToItemType_idx` (`item_type_pk` ASC),
  INDEX `fkStoreItemToStoreSection_idx` (`store_section_pk` ASC),
  CONSTRAINT `fkStoreItemToStore`
    FOREIGN KEY (`store_pk`)
    REFERENCES `storeguide`.`store` (`store_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkStoreItemToItemType`
    FOREIGN KEY (`item_type_pk`)
    REFERENCES `storeguide`.`global_item_type` (`item_type_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkStoreItemToStoreSection`
    FOREIGN KEY (`store_section_pk`)
    REFERENCES `storeguide`.`store_section` (`store_section_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);  
    
CREATE TABLE `storeguide`.`user_role_assoc` (
  `user_pk` INT NOT NULL,
  `role_pk` INT NOT NULL,
  INDEX `fkUserToRole_idx` (`role_pk` ASC),
  INDEX `fkUserRoleAssocToUser_idx` (`user_pk` ASC),
  CONSTRAINT `fkUserRoleAssocToRole`
    FOREIGN KEY (`role_pk`)
    REFERENCES `storeguide`.`role` (`role_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkUserRoleAssocToUser`
    FOREIGN KEY (`user_pk`)
    REFERENCES `storeguide`.`user` (`user_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
CREATE TABLE `storeguide`.`store_employee_assoc` (
  `store_pk` INT NOT NULL,
  `user_pk` INT NOT NULL,
  INDEX `fkStoreEmployeeAssocToStore_idx` (`store_pk` ASC),
  INDEX `fkStoreEmployeeAssocToUser_idx` (`user_pk` ASC),
  CONSTRAINT `fkStoreEmployeeAssocToStore`
    FOREIGN KEY (`store_pk`)
    REFERENCES `storeguide`.`store` (`store_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkStoreEmployeeAssocToUser`
    FOREIGN KEY (`user_pk`)
    REFERENCES `storeguide`.`user` (`user_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
CREATE TABLE `storeguide`.`store_item_type_assoc` (
  `store_pk` INT NOT NULL,
  `item_type_pk` INT NOT NULL,
  INDEX `fkStoreItemTypeAssocToStore_idx` (`store_pk` ASC),
  INDEX `fkStoreItemTypeAssocToItemType_idx` (`item_type_pk` ASC),
  CONSTRAINT `fkStoreItemTypeAssocToStore`
    FOREIGN KEY (`store_pk`)
    REFERENCES `storeguide`.`store` (`store_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkStoreItemTypeAssocToItemTypev`
    FOREIGN KEY (`item_type_pk`)
    REFERENCES `storeguide`.`global_item_type` (`item_type_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
CREATE TABLE `storeguide`.`favorite_items_assoc` (
  `user_pk` INT NOT NULL,
  `store_item_pk` INT NOT NULL,
  INDEX `fkFavoriteItemsAssocToUser_idx` (`user_pk` ASC),
  INDEX `fkFavoriteItemsAssocToStoreItem_idx` (`store_item_pk` ASC),
  CONSTRAINT `fkFavoriteItemsAssocToStoreItem`
    FOREIGN KEY (`store_item_pk`)
    REFERENCES `storeguide`.`store_item` (`store_item_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fkFavoriteItemsAssocToUser`
    FOREIGN KEY (`user_pk`)
    REFERENCES `storeguide`.`user` (`user_pk`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION);
  
ALTER TABLE `storeguide`.`user` 
   ADD COLUMN `password` VARCHAR(20) NOT NULL AFTER `username`,
   ADD COLUMN `login_enabled` CHAR(1) NOT NULL AFTER `password`;

INSERT INTO `storeguide`.`franchise` (`name`, `website`, `company_email`)
   VALUES ('Target', 'http://www.target.com', 'atlantatechie@gmail.com');







    
    