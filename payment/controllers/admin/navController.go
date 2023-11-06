package admin

import (
	"HMS/payment/models"

	"github.com/gin-gonic/gin"
)

type NavController struct {
	BaseController
}

func (con NavController) Index(c *gin.Context) {
	navList := []models.Nav{}

	models.DB.Find(&navList)
	c.JSON(200, gin.H{
		"result": navList,
	})
	// c.JSON(200, "Nav Index")
}
