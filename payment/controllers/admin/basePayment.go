package admin

import (
	"math/rand"
	"net/http"

	"github.com/gin-gonic/gin"
)

type payment interface {
	Pay()
}

type BasePaymentControl struct {
}

func (con BasePaymentControl) Pay(c *gin.Context) {
	payMethod := c.Query("payMethod")
	/*
	* {'DEBIT_CARD':debit, 'CREDIT_CARD':credit, 'MOBILE_PAY':mobile}
	 */
	if payMethod == "DEBIT_CARD" {
		DebitCardAccountController{}.Pay(c)
	} else if payMethod == "CREDIT_CARD" {
		CreditCardAccountController{}.Pay(c)
	} else if payMethod == "MOBILE_PAY" {
		MobileAccountController{}.Pay(c)
	} else {
		c.String(http.StatusOK, "wrong payment method")
	}
}

func (con BasePaymentControl) success(c *gin.Context) {
	c.JSON(http.StatusOK, gin.H{
		"message":            "success",
		"paymentReferenceId": rand.Intn(10000000),
	})
}
func (con BasePaymentControl) error(c *gin.Context, errInfo string) {
	if errInfo == "" {
		errInfo = "error"
	}
	c.JSON(http.StatusOK, gin.H{
		"message": "failure",
	})
}
