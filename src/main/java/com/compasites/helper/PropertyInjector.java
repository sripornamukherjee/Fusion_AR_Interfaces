package com.compasites.helper;

import java.io.IOException;
import java.util.Set;
 
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

public class PropertyInjector implements SOAPHandler<SOAPMessageContext>{

	public boolean handleMessage(SOAPMessageContext smc) {
		 
        Boolean outboundProperty = (Boolean) smc
                .get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
 
        SOAPMessage message = smc.getMessage();
 
        if (!outboundProperty.booleanValue()) {
            System.out.print(" SOAP Response ");
        } else {
            System.out.print(" SOAP Request ");
        }
 
        try {
            message.writeTo(System.out);
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("");
 
        return outboundProperty;
 
    }
 
    public Set getHeaders() {
        return null;
    }
 
    public boolean handleFault(SOAPMessageContext context) {
        return true;
    }
 
    public void close(MessageContext context) {
    }
}
