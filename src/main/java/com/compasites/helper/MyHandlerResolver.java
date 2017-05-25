package com.compasites.helper;

import java.util.ArrayList;
import java.util.List;
 
import javax.xml.ws.handler.HandlerResolver;
import javax.xml.ws.handler.PortInfo;
 
public class MyHandlerResolver implements HandlerResolver {
 
    public List getHandlerChain(PortInfo portInfo) {
        ArrayList<PropertyInjector> handlerChain = new ArrayList<PropertyInjector>();
        handlerChain.add(new PropertyInjector());
        return handlerChain;
    }
 
}