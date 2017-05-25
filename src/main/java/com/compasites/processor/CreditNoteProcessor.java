package com.compasites.processor;

import bsh.EvalError;
import bsh.Interpreter;
import com.compasites.constants.CreditnoteEnum;
import com.compasites.pojo.CreditNote;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Sobhan Babu on 26-05-2016.
 */
public class CreditNoteProcessor implements ItemProcessor<CreditNote, CreditNote> {

    static Logger LOG = LoggerFactory.getLogger(CreditNoteProcessor.class);

    @Value("${bsh.creditnote.mappingfile.location}")
    private String mappingFile;

    @Override
    public CreditNote process(final CreditNote creditnote) throws Exception {
        try {
            Interpreter i = new Interpreter(); // Construct an interpreter
            i.source(mappingFile);

            Map<String, String> map = new HashMap<String, String>();
            CreditnoteEnum.setValuesMap(creditnote, map);
            i.set("map", map);

            i.eval("mapCreditNoteValues(map)");
            map = (Map) i.get("map");

            CreditnoteEnum.setValuesCreditnote(creditnote, map);
        } catch (IOException ioe){
            LOG.error("Exception : " + ioe.getMessage());
            LOG.error("Exception occurred item data " + creditnote);
            throw ioe;
        } catch (EvalError evlError) {
            LOG.error("Exception : " + evlError.getMessage());
            LOG.error("Exception occurred item data " + creditnote);
            throw evlError;
        } catch (Exception e) {
            LOG.error("Exception : " + e.getMessage());
            LOG.error("Exception occurred item data " + creditnote);
            throw e;
        }

        return creditnote;
    }
}
