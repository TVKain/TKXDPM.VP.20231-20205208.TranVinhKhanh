-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2023-11-24 13:39:10.922

-- tables
-- Table: Book
CREATE TABLE Book (
    BookId integer NOT NULL CONSTRAINT Book_pk PRIMARY KEY,
    Author varchar(32) NOT NULL,
    PageNumber integer NOT NULL,
    Publisher varchar(32) NOT NULL,
    PublishDate date NOT NULL,
    Language varchar(32) NOT NULL,
    Category varchar(32) NOT NULL,
    Media_MediaId integer NOT NULL,
    CONSTRAINT Book_Media FOREIGN KEY (Media_MediaId)
    REFERENCES Media (MediaId)
);

-- Table: CD
CREATE TABLE CD (
    CDId integer NOT NULL CONSTRAINT CD_pk PRIMARY KEY,
    Artist varchar(32) NOT NULL,
    Label varchar(32) NOT NULL,
    Genre varchar(32) NOT NULL,
    ReleaseDate date NOT NULL,
    Media_MediaId integer NOT NULL,
    CONSTRAINT CD_Media FOREIGN KEY (Media_MediaId)
    REFERENCES Media (MediaId)
);

-- Table: DVD
CREATE TABLE DVD (
    DVDId integer NOT NULL CONSTRAINT DVD_pk PRIMARY KEY,
    Director varchar(32) NOT NULL,
    Studio varchar(32) NOT NULL,
    Runtime integer NOT NULL,
    ReleaseDate date NOT NULL,
    Language varchar(32) NOT NULL,
    Genre varchar(32) NOT NULL,
    Media_MediaId integer NOT NULL,
    CONSTRAINT DVD_Media FOREIGN KEY (Media_MediaId)
    REFERENCES Media (MediaId)
);

-- Table: DeliveryInfo
CREATE TABLE DeliveryInfo (
    DeliveryInfoId integer NOT NULL CONSTRAINT DeliveryInfo_pk PRIMARY KEY,
    Name varchar(32) NOT NULL,
    City varchar(32) NOT NULL,
    Instruction varchar(128) NULL,
    Address varchar(32) NOT NULL,
    Phone character(10) NOT NULL,
    RushOrder varchar(3) NOT NULL
);

-- Table: Invoice
CREATE TABLE Invoice (
    InvoiceId integer NOT NULL CONSTRAINT Invoice_pk PRIMARY KEY,
    Total integer NOT NULL,
    Subtotal integer NOT NULL,
    Vat integer NOT NULL,
    ShippingFee integer NOT NULL
);

-- Table: Media
CREATE TABLE Media (
    MediaId integer NOT NULL CONSTRAINT Media_pk PRIMARY KEY,
    Price integer NOT NULL,
    Value integer NOT NULL,
    Title varchar(64) NOT NULL,
    Category varchar(64) NOT NULL,
    Description varchar(256) NOT NULL,
    Quantity integer NOT NULL,
    Weight float NOT NULL
);

-- Table: Order
CREATE TABLE "Order" (
    OrderId integer NOT NULL CONSTRAINT Order_pk PRIMARY KEY,
    DeliveryInfo_DeliveryInfoId integer NOT NULL,
    Invoice_InvoiceId integer NOT NULL,
    CONSTRAINT Order_DeliveryInfo FOREIGN KEY (DeliveryInfo_DeliveryInfoId)
    REFERENCES DeliveryInfo (DeliveryInfoId),
    CONSTRAINT Order_Invoice FOREIGN KEY (Invoice_InvoiceId)
    REFERENCES Invoice (InvoiceId)
);

-- Table: OrderMedia
CREATE TABLE OrderMedia (
    Quantity integer NOT NULL,
    Order_OrderId integer NOT NULL,
    Media_MediaId integer NOT NULL,
    CONSTRAINT OrderMedia_pk PRIMARY KEY (Order_OrderId,Media_MediaId),
    CONSTRAINT OrderMedia_Order FOREIGN KEY (Order_OrderId)
    REFERENCES "Order" (OrderId),
    CONSTRAINT OrderMedia_Media FOREIGN KEY (Media_MediaId)
    REFERENCES Media (MediaId)
);

-- Table: Subtitle
CREATE TABLE Subtitle (
    SubtitleId integer NOT NULL CONSTRAINT Subtitle_pk PRIMARY KEY,
    Language varchar(32) NOT NULL,
    DVD_DVDId integer NOT NULL,
    CONSTRAINT Subtitle_DVD FOREIGN KEY (DVD_DVDId)
    REFERENCES DVD (DVDId)
);

-- Table: Track
CREATE TABLE Track (
    TrackId integer NOT NULL CONSTRAINT Track_pk PRIMARY KEY,
    Title varchar(32) NOT NULL,
    CD_CDId integer NOT NULL,
    CONSTRAINT Track_CD FOREIGN KEY (CD_CDId)
    REFERENCES CD (CDId)
);


INSERT INTO Media (Price, Value, Title, Category, Description, Quantity, Weight)
VALUES
    (10000, 100, 'Media 1', 'Category A', 'Description for Media 1', 5, 1.2),
    (15000, 150, 'Media 2', 'Category B', 'Description for Media 2', 8, 0.8),
    (20000, 200, 'Media 3', 'Category A', 'Description for Media 3', 10, 1.5),
    (25000, 250, 'Media 4', 'Category C', 'Description for Media 4', 12, 2.0),
    (30000, 300, 'Media 5', 'Category B', 'Description for Media 5', 15, 1.3),
    (35000, 350, 'Media 6', 'Category A', 'Description for Media 6', 7, 1.8),
    (40000, 400, 'Media 7', 'Category C', 'Description for Media 7', 9, 1.0),
    (45000, 450, 'Media 8', 'Category B', 'Description for Media 8', 11, 1.7),
    (50000, 500, 'Media 9', 'Category A', 'Description for Media 9', 6, 1.4),
    (55000, 550, 'Media 10', 'Category C', 'Description for Media 10', 14, 2.2),
    (60000, 600, 'Media 11', 'Category B', 'Description for Media 11', 20, 1.1),
    (65000, 650, 'Media 12', 'Category A', 'Description for Media 12', 3, 1.6),
    (70000, 700, 'Media 13', 'Category C', 'Description for Media 13', 17, 2.5),
    (75000, 750, 'Media 14', 'Category B', 'Description for Media 14', 22, 1.9),
    (80000, 800, 'Media 15', 'Category A', 'Description for Media 15', 13, 2.3),
    (85000, 850, 'Media 16', 'Category C', 'Description for Media 16', 16, 1.4),
    (90000, 900, 'Media 17', 'Category B', 'Description for Media 17', 18, 2.1),
    (95000, 950, 'Media 18', 'Category A', 'Description for Media 18', 25, 1.2),
    (100000, 1000, 'Media 19', 'Category C', 'Description for Media 19', 30, 1.8),
    (10500, 1050, 'Media 20', 'Category B', 'Description for Media 20', 21, 2.4);

-- End of file.

