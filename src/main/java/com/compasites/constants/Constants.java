package com.compasites.constants;

/**
 * Created by Sobhan Babu on 25-04-2016.
 */
public interface Constants {

    int LOOP_COUNT = 3;
    long THREAD_SLEEP_VALUE = 20000;

    String GET = "get";
    String SET = "set";
    String DATE_FORMAT = "yyyy-MM-dd";
    String ZIP = "zip";
    String DOCUMENT_SECURITY_GROUP = "FAFusionImportExport";
    String CVS_SEPERATOR = ",";
    String EMTPY = "";
    String SPACE = " ";
    String REGEX_ONE_OR_MORE_SPACE = " +";
    String SLASH = "/";
    String PERCENT_SIGN = "%";
    String DDMMYYYY_FORMAT = "dd-MM-yyyy";
    String LINE_TRANSACTION_FLEX_FIELD = "IS-2015-";
    String CM_LINE_TRANSACTION_FLEX_FIELD = "CM-";
    String TEMP_FILE = "temp.csv";
    String COMMA = ",";
    String DOUBLE_QUOTE = "\"";
    String TWO_DOUBLE_QUOTE = "\"\"";
    String MINUS = "-";
    String CSV = ".csv";
    String PAUSED = "Paused";
    String COMPLETED = "Completed";
    String RUNNING = "Running";
    String READY = "Ready";
    String SFC_MEMOLINE = "SFC Receivables";
    String WDA_MEMOLINE = "WDA Receivables";
    String FILE_DATE_FORMAT ="yyyyMMdd_HHmmss";
    String DATE_FORMAT_NEW = "yyyy/MM/dd";
    String CREDIT_MEMO_FLEX_SEG1 = "CM-";
    String CREDIT_MEMO_FLEX_SEG2 = "1";
    String RECEIPT_SID = "SID";
    String RECEIPT_DT_FORMAT = "yyMMdd";
    String NULL_FLAG = "null";
    String RECEIPT_BATCH_INITIAL = "BID";
    String FILE_NAME_DATEFORMAT = "ddMMyyyy";
    String EXTENSION_FILES = "csv";
    String TXT_EXTENSION_FILES = "txt";
    String CUSTOMER_FILENAME = "CUSTOMER_DDMMYYYY.csv";
    String INVOICE_FILENAME = "INV_DDMMYYY.csv";
    String RECEIPT_FILENAME = "RCPT_DDMMYYYY.csv";
    String CREDITMEMO_FILENAME = "CRN_25052016.csv";
    String JOURNAL_FILENAME = "UTI_25052016.csv";
    String BANKSTMT_FILENAME = "BSTMT_25052016.txt";

    //Account contacts Constants
    String ACCNT_CONTCTS_ROLE_TYPE = "CONTACT";
    String ACCNT_CONTCTS_PRIMARY_INDICATOR = "Y";
    String CSV_FLAG = "CSV";
    String N_FLAG = "N";
    String Y_FLAG = "Y";
    String ERROR_FLAG = "ERROR";
    String YES = "YES";
    String PAID_FLAG = "PAID";

    String CUSTOMER_DATE = "2000/01/01";
    String PARTY_SITE_USE_TYPE = "BILL_TO";
    String PRIMARY_INDICATOR = "Y";
    String ACCOUNT_TYPE = "R";
    String ACCOUNT_ADDRESS_PURPOSE_SET = "FMS_";
    String COUNTRY_SINGAPORE = "SG";
    String RELATIONSHIP_CODE = "CONTACT_OF";
    String CUSTOMER = "CUSTOMER";
    String PERSON = "PERSON";
    String ORG_CONTACT = "ORG_CONTACT";
    String TOLERANCE = "0";
    String MONTHLY = "Monthly";
    String CURRENCY_SINNGAPORE = "SGD";
    String PHONE = "PHONE";
    String SINGAPORE_PHONE_CODE = "65";
    String PHONE_LINE_TYPE = "GEN";
    String EMAIL = "EMAIL";
    String EMAIL_FORMAT = "MAILHTML";
    String BILLL_TO = "BILL_TO";
    String STMTS = "STMTS";
    String REGULAR_CM = "Regular Credit Memo";
    String END_FLAG = "END";
    String DEFAULT_FLAG = "DEFAULT";
    String DEFAULT_COLLECTOR_FLAG = "Default Collector";
    String UCM_SUCCESS_VALUE = "SUCCEEDED";
    String UCM_ERROR_VALUE = "error";
    String WDA_SFC_INVALID = "WDA/SFC Invalid";
    String RENEWAL_YEAR_INVALID = "Renewal Year Invalid";
    String INVALID_LOCKBOX = "Invalid Lockbox";

    String DECIMAL_FORMAT = "0.00";
    String BILL_DATE_ERROR_MSG = "Bill date should not be past date.";
    String COLLECTION_DATE_EMPTY_MSG = "Collection and Accounting date should not be empty.";
    String PAYMENTMODE_EMPTY_ERROR_MSG = "Payment mode should not be empty.";
    String INVALID_RECEIPTMETHOD = "Receipt method is invalid. Check payment mode for collection record.";
    String INVOICE_NUMBER_ERROR_MSG = "Invoice number should not be empty.";
    String COLLECTION_STATUS_ERROR_MSG = "Collection status value should be 'Paid'.";
    String CUSTOMER_ID_EMPTY_ERROR_MSG = "Customer Id should not be empty.";
    String RECEIPT_NUMBER_ERROR_MSG = "Receipt number should not be empty.";
    String INVOICE_AMT_EMPTY_ERROR_MSG = "Invoice amount should not be empty.";
    String RECEIPT_AMT_EMPTY_ERROR_MSG = "Receipt amount shoud not be empty.";
    String RECEIPT_AMOUNT_ERROR_MSG = "Receipt amount is not valid. Please check all amount formats.";
    String RECEIPT_INVOICE_AMOUNT_ERROR_MSG = "Invoice amount is not valid. Please check all amount formats.";
    String AMOUNT_ERROR_MSG = "Amounts are not valid. Please check all amount formats.";
    String GROSS_TOTAL_AMOUNT_ERROR_MSG = "Gross Total Amount should be equal to (Sum of line items * GST Percent).";
    String CUSTOMER_NOTAVAILABLE_ERROR_MSG = "Customer Id is not available in fusion.";
    String INVOICE_NOTAVAILABLE_ERROR_MSG = "Invoice number is not available in fusion.";
    String INVOICE_AVAILABLE_ERROR_MSG = "Invoice number is available in fusion.";
    String INVOICE_REF_EMPTY = "Invoice reference not provided for the credit memo.";
    String CREDITMEMO_AVAILABLE_ERROR_MSG = "CreditMemo number is available in fusion.";
    String CUSTOMER_STATUS_ERROR_MSG = "Customer status must be Active/Inactive.";
    String CUSTOMER_TYPE_ERROR_MSG = "Customer Type must be PERSON ACCOUNT/BUSINESS ACCOUNT.";
    String CUSTOMER_ID_EXIST_ERROR_MSG = "Customer ID is existing in Oracle Fusion.";
    String REVENUEACCOUNTCODE_EMPTY_ERROR_MSG = "Revenue account code value should not be empty.";
    String BILLDATE_EMPTY_ERROR_MSG = "Bill date and course start date should not be empty.";
    String AMT_EMPTY_ERROR_MSG = "Gross Total & GST & Allocated Revenue Amounts should not be empty.";
    String ALLOCATED_REVENUE_AMT_EMPTY_ERROR_MSG = "Allocated Revenue Amount should not be empty.";
    String FMS_TRANSACTION_TYPE_EMPTY_ERROR_MSG = "FMS transaction type should not be empty.";
    String TRANSACTION_TYPE_LINEITEM_EMPTY_ERROR_MSG = "Transaction Type Line Item should not be empty.";
    String CM_STATUS_EMPTY_ERROR_MSG = "Billing header status should not be empty.";
    String CM_PAYMENT_MODE_EMPTY_ERROR_MSG = "Payment mode should not be empty.";
    String INVALID_MEMO_LINE = "No valid memo line name for given Revenue A/C code.";
    String PAST_COURSE_START_DT = "Course start date cannot be empty/before invoice date.";
    String INVALID_AMT_ERROR_MSG = "Amount format is not valid. All amounts must have 2 decimal places.";
    String WDA_SFC_ERROR_MSG = "Both WDA and SFC amount columns cannot be filled.";
    String WDA_SFC_AMOUNT_ERROR_MSG = "Both WDA and SFC funding amount columns should not be empty.";
    String INVALID_RENEWAL_YR_ERROR_MSG = "Renewal Year should not be empty.";

    String CUSTOMER_ACCOUNT = "XXISCA_IF_Customer_Account";
    String INVOICE_NUMBER_ACCOUNT = "XXISCA_IF_AR_Transaction";
    String BATCH_IDENTIFIER_ACCOUNT = "XXISCA_IF_AR_Import_Batch";
    String INVOICE_LINE_REF_ACCOUNT = "XXISCA_IF_AR_Transaction_Line_Ref";
    String INVOICE_TAX_LINE_REF_ACCOUNT = "XXISCA_IF_AR_Transaction_Tax_Line_Ref";
    String CUSTOMER_REPORT_ABS_PATH = "Custom/ISCA Interfaces/Reports/XXISCA_IF_Customer_Account.xdo";
    String INVOICE_REPORT_ABS_PATH = "Custom/ISCA Interfaces/Reports/XXISCA_IF_AR_Transaction.xdo";
    String INVOICE_REF_REPORT_ABS_PATH = "Custom/ISCA Interfaces/Reports/XXISCA_IF_AR_Transaction_Ref.xdo";
    String INVOICE_LINE_REF_REPORT_ABS_PATH = "Custom/ISCA Interfaces/Reports/XXISCA_IF_AR_Transaction_Line_Ref.xdo";
    String INVOICE_TAX_LINE_REF_REPORT_ABS_PATH = "Custom/ISCA Interfaces/Reports/XXISCA_IF_AR_Transaction_Tax_Line_Ref.xdo";
    String BATCH_IDENTIFIER_ABS_PATH = "Custom/ISCA Interfaces/Reports/XXISCA_IF_AR_Import_Batch.xdo";
    String PR_CUSTOMER_NUMBER = "PR_CUSTOMER_NUMBER";
    String PR_BU_NAME = "PR_BU_NAME";
    String PR_TRX_NUMBER = "PR_TRX_NUMBER";
    String PR_BATCH_NAME = "PR_BATCH_NAME";
    String PR_CUST_TRX_ID = "PR_CUST_TRX_ID";
    String PR_UNIT_SELLING_PRICE = "PR_UNIT_SELLING_PRICE";
    String PR_LINE_TYPE = "PR_LINE_TYPE";
    String LINE_TYPE = "LINE";
    String INTERFACE_LINE_CONTEXT = "<INTERFACE_LINE_CONTEXT>";
    String INTERFACE_LINE_CONTEXT_END = "</INTERFACE_LINE_CONTEXT>";
    String INTERFACE_LINE_ATTRIBUTE1 = "<INTERFACE_LINE_ATTRIBUTE1>";
    String INTERFACE_LINE_ATTRIBUTE1_END = "</INTERFACE_LINE_ATTRIBUTE1>";
    String INTERFACE_LINE_ATTRIBUTE2 = "<INTERFACE_LINE_ATTRIBUTE2>";
    String INTERFACE_LINE_ATTRIBUTE2_END = "</INTERFACE_LINE_ATTRIBUTE2>";
    String UTF8 = "UTF-8";
    String CUST_ACCOUNT_ID = "<CUST_ACCOUNT_ID>";
    String CUSTOMER_TRX_ID = "<CUSTOMER_TRX_ID>";
    String CUSTOMER_TRX_ID_END = "</CUSTOMER_TRX_ID>";
    String BATCH_ID = "<BATCH_ID>";
    String BATCH_ID_END = "</BATCH_ID>";
    String BATCH_ID_INITIAL = "FMS-CRM-";
    String TAX_RATE_CODE = "OUTPUT-OSR_7";

    String IFLAG = "I";
    String AFLAG = "A";
    String ORGANIZATION = "ORGANIZATION";


    char INT_TO_BASE64[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3',
            '4', '5', '6', '7', '8', '9', '+', '/' };
	
}
