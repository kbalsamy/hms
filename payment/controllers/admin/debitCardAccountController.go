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

	if err != nil || value <= 0 {
		// do something sensible
		con.error(c, "wrong amount")
		return
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
	var ul *models.Debit
	u2 := models.DB.First(&ul, models.DB.Where("Accountnumber = ?", transferorId))

	if u2.Error != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}

	tx.Find(&ul, models.DB.Where("Accountnumber = ?", transferorId))
	ul.Balance = ul.Balance - amount
	if err := tx.Save(&ul).Error; err != nil || ul.Balance < 0 {
		tx.Rollback()
		con.error(c, "Payment account abnormal")
		return
	}
	// u3 := models.Debit{Accountnumber: payeeId}
	var u3 *models.Debit
	u4 := models.DB.First(&u3, models.DB.Where("Accountnumber = ?", payeeId))

	if u4.Error != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}
	tx.Find(&u3, models.DB.Where("Accountnumber = ?", payeeId))
	u3.Balance = u3.Balance + amount
	if err := tx.Save(&u3).Error; err != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal")
		return
	}

	tx.Commit()
	con.success(c)
}
