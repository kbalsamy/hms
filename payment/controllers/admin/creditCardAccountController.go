package admin

import (
	"HMS/payment/models"
	"fmt"
	"strconv"

	"github.com/gin-gonic/gin"
)

type CreditCardAccountController struct {
	BasePaymentControl
}

func (con CreditCardAccountController) Pay(c *gin.Context) {
	fmt.Println("-----credit")
	transferorId, _ := strconv.ParseInt(c.Query("transferorId"), 10, 64)
	payeeId, _ := strconv.ParseInt(c.Query("payeeId"), 10, 64)
	value, err := strconv.ParseFloat(c.Query("amount"), 32)

	if err != nil {
		// do something sensible
	}
	amount := float32(value)
	// begin a transaction
	tx := models.DB.Begin()
	defer func() {
		if r := recover(); r != nil {
			tx.Rollback()
			con.error(c, "failure of transaction")
		}
	}()
	// transferor going to transfer mondy to someone
	ul := models.Credit{Id: transferorId}
	tx.Find(&ul)
	ul.Amount = ul.Amount + amount
	if err := tx.Save(&ul).Error; err != nil {
		tx.Rollback()
		con.error(c, "Payment account abnormal")
		return
	}
	// panic("exception")
	//payee get incremented money amount
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
