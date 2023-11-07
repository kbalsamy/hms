package admin

import (
	"net/http"

	"github.com/gin-gonic/gin"
)

type BasePaymentControl struct {
}

func (con BasePaymentControl) Pay(c *gin.Context) {
	payMethod := c.Query("payMethod")
	/*
	* {1:debit, 2:credit, 3:mobile}
	 */
	if payMethod == "1" {
		DebitCardAccountController{}.Pay(c)
	} else if payMethod == "2" {
		CreditCardAccountController{}.Pay(c)
	} else if payMethod == "3" {
		MobileAccountController{}.Pay(c)
	} else {
		c.String(http.StatusOK, "wrong payment method")
	}
}

func (con BasePaymentControl) success(c *gin.Context) {
	c.String(http.StatusOK, "success")
}
func (con BasePaymentControl) error(c *gin.Context, errInfo string) {
	if errInfo == "" {
		errInfo = "error"
	}
	c.String(http.StatusOK, errInfo)
}
