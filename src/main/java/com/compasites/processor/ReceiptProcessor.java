package com.compasites.processor;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.ReceiptEnum;
import com.compasites.pojo.Receipt;
import com.compasites.validations.CommonValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sobhan Babu on 26-04-2016.
 */
public class ReceiptProcessor implements ItemProcessor<Receipt, Receipt> {

    static Logger LOG = LoggerFactory.getLogger(ReceiptProcessor.class);

    @Value("${bsh.receipt.mappingfile.location}")
    private String mappingFile;

    @Override
    public Receipt process(final Receipt receipt) throws Exception {
        try {
            Interpreter i = new Interpreter(); // Construct an interpreter
            i.source(mappingFile);

            Map<String, String> map = new HashMap<String, String>();
            ReceiptEnum.setValuesMap(receipt, map);
            i.set("map", map);

            i.eval("mapReceiptValues(map)");
            map = (Map) i.get("map");

            ReceiptEnum.setValuesReceipt(receipt, map);

            //validation
            boolean isValidAmt = CommonValidation.amtDecimalLen(receipt.getCollectionAmt());
            DecimalFormat df = new DecimalFormat("0.00");
            boolean isValidInvoiceAmt = CommonValidation.amtDecimalLen(receipt.getGrossTotAmt());
            if (isValidAmt && isValidInvoiceAmt) {
                receipt.setGrossTotAmt(df.format(new BigDecimal(receipt.getGrossTotAmt())));
                receipt.setCollectionAmt(df.format(new BigDecimal(receipt.getCollectionAmt())));
                receipt.setValid(true);
            }
        } catch (IOException ioe){
            LOG.error("Exception message : " + ioe.getMessage());
            LOG.error("IOException : ", ioe);
            throw ioe;
        } catch (EvalError evlError) {
            LOG.error("Exception message : " + evlError.getMessage());
            LOG.error("EvalError Exception : ", evlError);
            throw evlError;
        } catch (Exception e) {
            LOG.error("Exception : " + e.getMessage());
            LOG.error("Exception : ", e);
            throw e;
        }

        return receipt;
    }
}
