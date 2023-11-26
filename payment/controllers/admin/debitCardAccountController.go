package admin

import (
	"HMS/payment/models"
	"fmt"
	"strconv"

	"github.com/gin-gonic/gin"
)

type DebitCardAccountController struct {
	BasePaymentControl
}

func (con DebitCardAccountController) Pay(c *gin.Context) {
	fmt.Println("=====debit")
	transferorId, _ := strconv.Atoi(c.Query("transferorId"))
	payeeId, _ := strconv.Atoi(c.Query("payeeId"))
	value, err := strconv.ParseFloat(c.Query("amount"), 32)

	if err != nil {
		// do something sensible
	}
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
	// select * from debit
	// transferor going to transfer mondy to someone
	ul := models.Debit{Id: transferorId}
	tx.Find(&ul)
	ul.Balance = ul.Balance - amount
	if err := tx.Save(&ul).Error; err != nil {
		tx.Rollback()
		con.error(c, "Payment account abnormal")
		return
	}
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
