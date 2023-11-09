package routers

import (
	"HMS/payment/controllers/admin"

	"github.com/gin-gonic/gin"
)

func AdminRoutersInit(r *gin.Engine) {
	adminRouters := r.Group("/")
	// r.Use(initMiddleware1)  //config
	//, middlewares.JWT
	{
		adminRouters.GET("/pay", admin.BasePaymentControl{}.Pay)
		// adminRouters.GET("/debit", admin.BasePaymentControl{}.Pay)
	}
}
