package models

type Credit struct {
	Id         int
	Username   string
	Expirydate string
	Amount     float32
}

func (Credit) TableName() string {
	return "credit"
}
