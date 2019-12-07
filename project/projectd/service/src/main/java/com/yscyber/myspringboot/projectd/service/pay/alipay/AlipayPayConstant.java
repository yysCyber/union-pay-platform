package com.yscyber.myspringboot.projectd.service.pay.alipay;

/**
 * 支付宝支付所要用到的常量
 *
 * EXAMPLE 作为前缀的为“示例常量”
 *
 * @author Yuan
 */
public interface AlipayPayConstant {

    /**
     * 示例用，应用 ID
     */
    String EXAMPLE_APP_ID = "2018062960540016";

    /**
     * 示例用，商户私钥
     *
     * 发起支付时由商户私钥进行签名
     */
    String EXAMPLE_MERCHANT_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCC/tk1oqymli5HpP9MxOkNofKkON1a6EqKg/S0++mZoKLInLasLkNuz0F8nSg/nBZGMv8XcIOXeOsK1ZT9tibxlgzun22lnBpHLueCAZlqITx33XO8SwiVULar2NIVFJwuUdvHPf/2Wj361cTd+zw+uJTPy3vIBljeOHDajzqBg9m5JrCIOgUze+4MZBT5/74gwwwpMDhRHRfR+4FTFmxqqzbTBGaFv4gCLSRB/3VpuruAP5AKCTR2ATHXgFO9veCemNwx5sI3F9Tw43+16xzAZh2T/6Xb3d31H9k31h9Iai7t2s00qY6eKsFpdi4ySK5qB2G9lbFvbpBv+3gz/5UFAgMBAAECggEANWn7Yc5yPf8w4c/atg/4w7FMgkAZqm7brj1+M6ogp58DoW+dYPoATxe9qTVf6wgXYQp8T692SPZqSOGsPgPfP1Ui8s4ZZJURdgMfLTi/uLHSuUGYZqfl2RSvV8UybJwZzx1b05NpYqeLbFNsUeOWvjB/pXdggsBrzjkPc19ByPtERzjiEFzvxGdYPDdvgNUmvbQj647X8umdyDAcNAbd8rkSQW87zBT12OQ/i/QR74BBg4VBJqTg9APk9mdVNufvxMwi9+lyGsGRFdnjmPCLHJvnT3Nh9KSHWuLrOeUjkGVEozd40iz/8LVwr+BZNeL6EjtZ3ztfphnIl92lMFNhQQKBgQDlm0t5N0REM0al/L0BygkwLsHHvAmjYhzjDJeooLgH0NyYYP/ruqqF6oPxDDaw17pciEypyOdoQ1JFaw9Uq41PfZlqL74PehSD3KWe7pPzlWIukaDq1aPit/pox92+ZO0lqwHQa80Tf1PO55xAVJQPltrhScnAmZiVIG1TD9z+sQKBgQCSDbGW2lVx5b9s1R0l8SRvoHQc83iIIyqx/+UJEEwxEMwSJ4ygAfUPcrJOYs2gOB9Ny08xqtNEQKlghwgwFqIec705AiU67Bw3TjWHnxgzvhzLPCrty/yJbWMmpXDjMm/VvwASkBL6pijjh/GUrRsJvh0McgWkDIg8OgIRu5rYlQKBgQChJ+nGb7sTn2XT9Vv4GIVwczEB7wJY0fFyj5EXA4+HtNpQfazDGOa9TchD9Q2h/BjK/8PHW8LIVJA3NxiwliR+CasXc+ET3dzuXH1G2y+vRUd/ZimrCj6YUAeLadVC4HXu/WMtlayAJdt+GuR55qNxebGxdOgNrgEBkpwJM9YqUQKBgCvFcNit/HeTDEiYohCx8WKG6uWWTiQ2reAEueZ6fOsjhpVWRv3ZOFF15Vw6njeLOk59RPG0qXZGDr0AGwMWdWW8+BOyweejxV0J0l8f3gf7zPNXx+HWhYvGPbXiVS+x+PRNNr9ZcGawD4cJQex16KmF0XzeWzRsERRDqkUYPXL1AoGBAJVzao5Irdt0wj/R8NG3uVeEMbESNLKNLZ7M9hPaZn2xacoPHXXLKg2m546RYNOngYLB5l9dbpake5BoXtuGylmtD1tZvGSxLmznfrxQpZr0vuR2iCIgsVGdPhJlIRbKyeocofsRdaXgD413O4MCi/WmJ4E1ydyOqv+2cCQQAJBB";

    /**
     * 示例用，支付宝公钥
     *
     * 接收异步通知（支付结果通知）时，由支付宝公钥进行验签
     */
    String EXAMPLE_ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtojdtkETo4OEsQLeyyPwtWK9ZqYJANq6jjXC74vk9n/r88yW577y7VdxcK9X/F/wvR7D8of7lndYdhg6xZro0eO2skPZTU+A549J7tfzahVbIBAS+x1WPFJwPtVrfBBvkwHL8PT+YnMcxKyBxOa6wo8fzJs1NgU1+qnDCpwUFyv59GUfdzBvTPL1fY3ZzvRHFHbapevVltbO/jNV0thb8dafmcJXl8lnjQy3XlH3eTH28tlVfqickacfRl/WSD8WN3dGgF7dTDKYfSR7YB7jsHe6VzoHM3UnD9/yQbi/Z3ZrL7yOxEjq4tfrKlZIW7ZCoUpOU4QdPIRhLeC6nWyGrQIDAQAB";

    /**
     * 示例用，订单名称前缀
     */
    String EXAMPLE_ORDER_NAME_PREFIX = "5328260-";

    /**
     * 示例用，接收来自支付宝支付通知（含支付最终结果等等）的 URL
     *
     * 最终的支付结果，需要以来自支付宝支的支付通知为准！！！
     * 要求：外网必须能够访问到
     */
    String EXAMPLE_NOTIFY_URL = "http://yscyber.natapp1.cc/union-pay/alipay/pc/async-notify";

    /**
     * 示例用，支付成功后将要跳转的 URL
     */
    String EXAMPLE_RETURN_URL = "http://yscyber.natapp1.cc/union-pay/alipay/pc/temp-success";

    /**
     * 告诉支付宝，不要再发送异步通知了
     * 来自官方文档：https://docs.open.alipay.com/270/105902
     */
    String NO_NEED_NOTIFY_FLAG = "success";

}