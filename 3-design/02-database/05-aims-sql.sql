-- tables
-- Table: Book
CREATE TABLE Book (
    MediaId int  NOT NULL,
    Author varchar(32)  NOT NULL,
    PageNumber int  NOT NULL,
    Publisher varchar(32)  NOT NULL,
    PublishDate date  NOT NULL,
    Language varchar(32)  NOT NULL,
    Category varchar(32)  NOT NULL,
    CONSTRAINT Book_pk PRIMARY KEY (MediaId)
);

-- Table: CD
CREATE TABLE CD (
    MediaId int  NOT NULL,
    Artist varchar(32)  NOT NULL,
    Label varchar(32)  NOT NULL,
    Genre varchar(32)  NOT NULL,
    ReleaseDate date  NOT NULL,
    CONSTRAINT CD_pk PRIMARY KEY (MediaId)
);

-- Table: Card
CREATE TABLE Card (
    CardId int  NOT NULL,
    CardNumber varchar(32)  NOT NULL,
    CardHolder varchar(32)  NOT NULL,
    CVV varchar(8)  NOT NULL,
    CONSTRAINT Card_pk PRIMARY KEY (CardId)
);

-- Table: DVD
CREATE TABLE DVD (
    MediaId int  NOT NULL,
    Director varchar(32)  NOT NULL,
    Studio varchar(32)  NOT NULL,
    Runtime int  NOT NULL,
    ReleaseDate date  NOT NULL,
    Language varchar(32)  NOT NULL,
    Genre varchar(32)  NOT NULL,
    CONSTRAINT DVD_pk PRIMARY KEY (MediaId)
);

-- Table: DeliveryInfo
CREATE TABLE DeliveryInfo (
    DeliveryInfoId int  NOT NULL,
    Name int  NOT NULL,
    Instruction varchar(128)  NOT NULL,
    Address varchar(32)  NOT NULL,
    CONSTRAINT DeliveryInfo_pk PRIMARY KEY (DeliveryInfoId)
);

-- Table: Invoice
CREATE TABLE Invoice (
    InvoiceId int  NOT NULL,
    Total int  NOT NULL,
    Subtotal int  NOT NULL,
    Vat int  NOT NULL,
    CONSTRAINT Invoice_pk PRIMARY KEY (InvoiceId)
);

-- Table: Media
CREATE TABLE Media (
    MediaId int  NOT NULL,
    Price int  NOT NULL,
    Value int  NOT NULL,
    Title varchar(64)  NOT NULL,
    Category varchar(64)  NOT NULL,
    Description varchar(256)  NOT NULL,
    CONSTRAINT Media_pk PRIMARY KEY (MediaId)
);

-- Table: Order
CREATE TABLE `Order` (
    OrderId int  NOT NULL,
    ShippingFee int  NOT NULL,
    DeliveryInfoId int  NOT NULL,
    InvoiceId int  NOT NULL,
    CONSTRAINT Order_pk PRIMARY KEY (OrderId)
);

-- Table: OrderMedia
CREATE TABLE OrderMedia (
    Price int  NOT NULL,
    Quantity int  NOT NULL,
    OrderId int  NOT NULL,
    MediaId int  NOT NULL,
    CONSTRAINT OrderMedia_pk PRIMARY KEY (OrderId,MediaId)
);

-- Table: PaymentTransaction
CREATE TABLE PaymentTransaction (
    PaymentTransactionId int  NOT NULL,
    CreatedAt int  NOT NULL,
    Content int  NOT NULL,
    Method int  NOT NULL,
    InvoiceId int  NOT NULL,
    CardId int  NOT NULL,
    CONSTRAINT PaymentTransaction_pk PRIMARY KEY (PaymentTransactionId)
);

-- Table: PhysicalMedia
CREATE TABLE PhysicalMedia (
    MediaId int  NOT NULL,
    Barcode char(12)  NOT NULL,
    ImportDate date  NOT NULL,
    Quantity int  NOT NULL,
    Dimension int  NOT NULL,
    CONSTRAINT PhysicalMedia_pk PRIMARY KEY (MediaId)
);

-- Table: Subtitle
CREATE TABLE Subtitle (
    SubtitleId int  NOT NULL,
    Language varchar(32)  NOT NULL,
    DVDId int  NOT NULL,
    CONSTRAINT Subtitle_pk PRIMARY KEY (SubtitleId)
);

-- Table: Track
CREATE TABLE Track (
    TrackId int  NOT NULL,
    Title varchar(32)  NOT NULL,
    CDId int  NOT NULL,
    CONSTRAINT Track_pk PRIMARY KEY (TrackId)
);

-- foreign keys
-- Reference: Book_PhysicalMedia (table: Book)
ALTER TABLE Book ADD CONSTRAINT Book_PhysicalMedia FOREIGN KEY Book_PhysicalMedia (MediaId)
    REFERENCES PhysicalMedia (MediaId);

-- Reference: CD_PhysicalMedia (table: CD)
ALTER TABLE CD ADD CONSTRAINT CD_PhysicalMedia FOREIGN KEY CD_PhysicalMedia (MediaId)
    REFERENCES PhysicalMedia (MediaId);

-- Reference: Card_PaymentTransaction (table: PaymentTransaction)
ALTER TABLE PaymentTransaction ADD CONSTRAINT Card_PaymentTransaction FOREIGN KEY Card_PaymentTransaction (CardId)
    REFERENCES Card (CardId);

-- Reference: DVD_PhysicalMedia (table: DVD)
ALTER TABLE DVD ADD CONSTRAINT DVD_PhysicalMedia FOREIGN KEY DVD_PhysicalMedia (MediaId)
    REFERENCES PhysicalMedia (MediaId);

-- Reference: Invoice_Order (table: Order)
ALTER TABLE `Order` ADD CONSTRAINT Invoice_Order FOREIGN KEY Invoice_Order (InvoiceId)
    REFERENCES Invoice (InvoiceId);

-- Reference: OrderMedia_Media (table: OrderMedia)
ALTER TABLE OrderMedia ADD CONSTRAINT OrderMedia_Media FOREIGN KEY OrderMedia_Media (MediaId)
    REFERENCES Media (MediaId);

-- Reference: OrderMedia_Order (table: OrderMedia)
ALTER TABLE OrderMedia ADD CONSTRAINT OrderMedia_Order FOREIGN KEY OrderMedia_Order (OrderId)
    REFERENCES `Order` (OrderId);

-- Reference: Order_DeliveryInfo (table: Order)
ALTER TABLE `Order` ADD CONSTRAINT Order_DeliveryInfo FOREIGN KEY Order_DeliveryInfo (DeliveryInfoId)
    REFERENCES DeliveryInfo (DeliveryInfoId);

-- Reference: PaymentTransaction_Invoice (table: PaymentTransaction)
ALTER TABLE PaymentTransaction ADD CONSTRAINT PaymentTransaction_Invoice FOREIGN KEY PaymentTransaction_Invoice (InvoiceId)
    REFERENCES Invoice (InvoiceId);

-- Reference: PhysicalMedia_Media (table: PhysicalMedia)
ALTER TABLE PhysicalMedia ADD CONSTRAINT PhysicalMedia_Media FOREIGN KEY PhysicalMedia_Media (MediaId)
    REFERENCES Media (MediaId);

-- Reference: Subtitle_DVD (table: Subtitle)
ALTER TABLE Subtitle ADD CONSTRAINT Subtitle_DVD FOREIGN KEY Subtitle_DVD (DVDId)
    REFERENCES DVD (MediaId);

-- Reference: Track_CD (table: Track)
ALTER TABLE Track ADD CONSTRAINT Track_CD FOREIGN KEY Track_CD (CDId)
    REFERENCES CD (MediaId);

