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
	transferorId, _ := strconv.ParseInt(c.Query("transferorId"), 10, 64)
	payeeId, _ := strconv.ParseInt(c.Query("payeeId"), 10, 64)
	value, err := strconv.ParseFloat(c.Query("amount"), 32)

	if err != nil {
		// do something sensible
	}
	var debit *models.Debit
	amount := float32(value)
	// begin a transaction
	tx := models.DB.Begin()
	defer func() {
		if r := recover(); r != nil {
			tx.Rollback()
			con.error(c, "failure of transaction")
		}
	}()
	u1 := models.DB.First(&debit, transferorId)
	if u1.Error != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}
	// transferor going to transfer mondy to someone
	ul := models.Debit{Id: transferorId}
	tx.Find(&ul)
	ul.Balance = ul.Balance - amount
	if err := tx.Save(&ul).Error; err != nil {
		tx.Rollback()
		con.error(c, "Payment account abnormal")
		return
	}

	u2 := models.DB.First(&debit, payeeId)

	if u2.Error != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}
	u3 := models.Debit{Id: payeeId}
	tx.Find(&u3)
	u3.Balance = u3.Balance + amount
	if err := tx.Save(&u3).Error; err != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}

	tx.Commit()
	con.success(c)
}
