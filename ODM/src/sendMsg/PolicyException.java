/**
 * PolicyException.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis2 version: 1.7.5  Built on : May 06, 2017 (03:45:26 BST)
 */
package sendMsg;

public class PolicyException extends java.lang.Exception {
    private static final long serialVersionUID = 1574759477223L;
    private sendMsg.Cmcc_mas_wbsStub.PolicyExceptionE faultMessage;

    public PolicyException() {
        super("PolicyException");
    }

    public PolicyException(java.lang.String s) {
        super(s);
    }

    public PolicyException(java.lang.String s, java.lang.Throwable ex) {
        super(s, ex);
    }

    public PolicyException(java.lang.Throwable cause) {
        super(cause);
    }

    public void setFaultMessage(sendMsg.Cmcc_mas_wbsStub.PolicyExceptionE msg) {
        faultMessage = msg;
    }

    public sendMsg.Cmcc_mas_wbsStub.PolicyExceptionE getFaultMessage() {
        return faultMessage;
    }
}
