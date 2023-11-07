package models

type Debit struct {
	Id         int
	Username   string
	Expirydate string
	Balance    float32
}

func (Debit) TableName() string {
	return "debit"
}
