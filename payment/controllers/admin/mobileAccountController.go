package admin

import (
	"HMS/payment/models"
	"fmt"
	"strconv"

	"github.com/gin-gonic/gin"
)

type MobileAccountController struct {
	BasePaymentControl
}

func (con MobileAccountController) Pay(c *gin.Context) {
	fmt.Println("+++++mobile")
	transferorId := c.Query("transferorId")
	payeeId, _ := strconv.ParseInt(c.Query("payeeId"), 10, 64)
	value, err := strconv.ParseFloat(c.Query("amount"), 32)

	if err != nil {
		// do something sensible
	}
	var mobile *models.Mobile
	var debit *models.Debit
	amount := float32(value)
	// amount := float32(c.Query("amount"))
	// begin a transaction
	tx := models.DB.Begin()
	defer func() {
		if r := recover(); r != nil {
			tx.Rollback()
			con.error(c, "failure of transaction")
		}
	}()
	//perform some db operations in the transaction（From here，I should use"tx" instead of "db"）
	//user transfer money to hospital
	// transferor going to transfer mondy to someone
	u1 := models.DB.First(&mobile, transferorId)
	if u1.Error != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}
	ul := models.Mobile{Id: transferorId}
	tx.Find(&ul)
	ul.Balance = ul.Balance - amount
	if err := tx.Save(&ul).Error; err != nil {
		tx.Rollback()
		con.error(c, "Payment account abnormal")
		return
	}
	// panic("exception")
	//hospital account increasedy by amount
	//payee get incremented money amount
	u3 := models.DB.First(&debit, payeeId)

	if u3.Error != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}
	u2 := models.Debit{Id: payeeId}
	tx.Find(&u2)
	u2.Balance = u2.Balance + amount
	if err := tx.Save(&u2).Error; err != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}

	tx.Commit()
	con.success(c)
}
