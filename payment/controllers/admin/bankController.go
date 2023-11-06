package admin

import (
	"HMS/payment/models"

	"github.com/gin-gonic/gin"
)

type BankController struct {
	BaseController
}

func (con BankController) Index(c *gin.Context) {
	// con.success(c)
	// zhangsan transfers money to lisi
	// c.String(http.StatusOK, "bank")
	// begin a transaction
	tx := models.DB.Begin()
	defer func() {
		if r := recover(); r != nil {
			tx.Rollback()
			con.error(c)
		}
	}()
	//在事务中执行一些db操作（从这里开始，您应该使用"tx" 而不是"db"）
	//张三给李四转账
	ul := models.Bank{Id: 1}
	tx.Find(&ul)
	ul.Balance = ul.Balance - 100
	// fmt.println("账户余额" + ul.Balance)
	if err := tx.Save(&ul).Error; err != nil {
		tx.Rollback()
		con.error(c)
		return
	}
	// panic("exception")
	//在李四账号增加100
	u2 := models.Bank{Id: 2}
	tx.Find(&u2)
	u2.Balance = u2.Balance + 100
	if err := tx.Save(&u2).Error; err != nil {
		tx.Rollback()
		con.error(c)
		return
	}

	tx.Commit()
	con.success(c)
}
