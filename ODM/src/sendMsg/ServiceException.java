/**
 * ServiceException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:26 BST)
 */
package sendMsg;

public class ServiceException extends java.lang.Exception {
    private static final long serialVersionUID = 1574759477162L;
    private sendMsg.Cmcc_mas_wbsStub.ServiceExceptionE faultMessage;

    public ServiceException() {
        super("ServiceException");
    }

    public ServiceException(java.lang.String s) {
        super(s);
    }

    public ServiceException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public ServiceException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(sendMsg.Cmcc_mas_wbsStub.ServiceExceptionE msg) {
        faultMessage = msg;
    }

    public sendMsg.Cmcc_mas_wbsStub.ServiceExceptionE getFaultMessage() {
        return faultMessage;
    }
}
