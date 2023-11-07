package routers

import (
	"HMS/payment/controllers/admin"
	"HMS/payment/middlewares"

	"github.com/gin-gonic/gin"
)

func AdminRoutersInit(r *gin.Engine) {
	adminRouters := r.Group("/", middlewares.JWT)
	// r.Use(initMiddleware1)  //config
	{
		adminRouters.GET("/pay", admin.BasePaymentControl{}.Pay)
		// adminRouters.GET("/debit", admin.BasePaymentControl{}.Pay)
	}
}
