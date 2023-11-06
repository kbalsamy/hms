package models

type User struct {
	Id       int
	Username string
	Age      int
	Emai     string
	AddTime  int
}

func (User) TableName() string {
	return "user"
}
