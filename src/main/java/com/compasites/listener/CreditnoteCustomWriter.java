package com.compasites.listener;

import com.compasites.helper.CreditNoteInterfaceHelper;
import com.compasites.helper.ReceiptInterfacHelper;
import com.compasites.pojo.CreditNote;
import com.compasites.pojo.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * Created by Sobhan Babu on 26-05-2016.
 */
public class CreditnoteCustomWriter implements ItemWriter<CreditNote> {

    static Logger LOG = LoggerFactory.getLogger(CreditnoteCustomWriter.class);

    @Override
    public void write(List<? extends CreditNote> items) throws Exception {
        CreditNoteInterfaceHelper creditNoteHelper = new CreditNoteInterfaceHelper();
        for (CreditNote creditnote : items) {
            LOG.debug("creditnote values : " + creditnote);
            creditNoteHelper.createCreditNote(creditnote);
        }
    }
}
