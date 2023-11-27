package admin

import (
	"HMS/payment/models"
	"fmt"

	"github.com/gin-gonic/gin"
)

type MobileAccountController struct {
	BasePaymentControl
}

func (con MobileAccountController) Pay(c *gin.Context) {
	fmt.Println("+++++mobile")
	transferorId := c.Query("transferorId")
	_, payeeId, amount, err := con.getParameters(c)
	if err != nil {
		return
	}
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
	var ul *models.Mobile
	u1 := models.DB.First(&ul, models.DB.Where("Phonenumber = ?", transferorId))
	if u1.Error != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal2")
		return
	}
	tx.Find(&ul, models.DB.Where("Phonenumber = ?", transferorId))
	// ul := models.Mobile{Id: transferorId}
	// tx.Find(&ul)
	ul.Balance = ul.Balance - amount
	if err := tx.Save(&ul).Error; err != nil || ul.Balance < 0 {
		tx.Rollback()
		con.error(c, "Payment account abnormal")
		return
	}
	// panic("exception")
	//hospital account increasedy by amount
	//payee get incremented money amount
	var u2 *models.Debit
	u4 := models.DB.First(&u2, models.DB.Where("Accountnumber = ?", payeeId))

	if u4.Error != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal3")
		return
	}
	tx.Find(&u2, models.DB.Where("Accountnumber = ?", payeeId))
	u2.Balance = u2.Balance + amount
	if err := tx.Save(&u2).Error; err != nil {
		tx.Rollback()
		con.error(c, "Collection account abnormal1")
		return
	}

	tx.Commit()
	con.success(c)
}
