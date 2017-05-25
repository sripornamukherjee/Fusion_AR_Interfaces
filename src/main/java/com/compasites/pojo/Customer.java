package com.compasites.pojo;

import com.compasites.constants.Constants;

/**
 * Created by Sobhan Babu on 21-04-2016.
 */
public class Customer {

    public static String errorTime;
    public static String batchId;
    public static String customerFile;
    private String customerTypeInput;

    //new input
    private String srcSysId;
    private String customerType;
    private String customerId;
    private String customerId1;
    private String customerName;
    private String contactPersonName;
    private String contactPersonName1;
    private String emailAddress;
    private String contactPhoneNumber;
    private String billingAddr1;
    private String billingAddr2;
    private String billingAddr3;
    private String billingAddr4;
    private String city;
    private String inputState;
    private String country;
    private String postalCode;
    private String effectiveFrom;
    private String customerStatus;
    private String customerStatusInput;

    private String errorMsg = Constants.EMTPY;
    private String mappedEmailAddr;

    //parties
    private String batchIdentifier;
    private String prtyOrigSystem = Constants.CSV_FLAG;
    private String insertUpdateIndicator;
    private String partyUsageCode;
    private String prtyNumber;
    private String salutation;
    private String taxPayer;
    private String dunsNum;
    private String personFrstNm;
    private String personLstNm;
    private String personLstNmPrefix;
    private String personSecndLstNm;
    private String personMidlNm;
    private String personNmSuffix;
    private String personTitle;
    private String descFixfieldSeg;

    //party sites
    private String partySiteName="PRIMARY";
    private String prtySiteOrgSystem = Constants.CSV_FLAG;
    private String locOriginalSystem = Constants.CSV_FLAG;
    private String prtySiteFromDt = Constants.CUSTOMER_DATE;
    private String prtySiteNum;
    private String prtySiteToDt;
    private String mailStop;
    private String identifyingAddr = Constants.Y_FLAG;
    private String siteLanguage;
    private String relationSrcSys;
    private String relationSrcSysRef;

    //party site uses
    private String prtSiteUseType = Constants.PARTY_SITE_USE_TYPE;
    private String primaryIndicator = Constants.PRIMARY_INDICATOR;
    private String fromDt = Constants.CUSTOMER_DATE;
    private String toDt;
    private String prtySiteUseOrgSys = Constants.CSV_FLAG;

    //accounts
    private String custAccntSrcSys = Constants.CSV_FLAG;
    private String accntNum;
    private String accntType  = Constants.ACCOUNT_TYPE;
    private String custClass;
    private String accntDesc;
    private String accntEstDt = Constants.CUSTOMER_DATE;
    private String userDefcontxtPrompt;
    private String descrFlexfieldSeg1;
    private String descrFlexfieldSeg2;
    private String descrFlexfieldSeg3;
    private String descrFlexfieldSeg4;
    private String descrFlexfieldSeg5;
    private String descrFlexfieldSeg6;
    private String descrFlexfieldSeg7;
    private String descrFlexfieldSeg8;
    private String descrFlexfieldSeg9;
    private String descrFlexfieldSeg10;
    private String descrFlexfieldSeg11;
    private String descrFlexfieldSeg12;
    private String descrFlexfieldSeg13;
    private String descrFlexfieldSeg14;
    private String descrFlexfieldSeg15;
    private String descrFlexfieldSeg16;
    private String descrFlexfieldSeg17;
    private String descrFlexfieldSeg18;
    private String descrFlexfieldSeg19;
    private String descrFlexfieldSeg20;
    private String descrFlexfieldSeg21;
    private String descrFlexfieldSeg22;
    private String descrFlexfieldSeg23;
    private String descrFlexfieldSeg24;
    private String descrFlexfieldSeg25;
    private String descrFlexfieldSeg26;
    private String descrFlexfieldSeg27;
    private String descrFlexfieldSeg28;
    private String descrFlexfieldSeg29;
    private String descrFlexfieldSeg30;
    private String accntTerminationDt;

    //account sites
    private String accntSiteSrcSys = Constants.CSV_FLAG;
    private String accntSiteSrcSysRef;
    private String custCategoryCd;
    private String transalatedCustNm;
    private String accntAddrSet = Constants.ACCOUNT_ADDRESS_PURPOSE_SET;
    private String keyAccnt;

    //account site uses
    private String accntSitePurposeSrcSys = Constants.CSV_FLAG;
    private String accntSitePurposeSrcSysRef;
    private String purpose = Constants.PARTY_SITE_USE_TYPE;
    private String purposeFromDt = Constants.CUSTOMER_DATE;;
    private String purposeToDt;
    private String accntAddPurposeSet = Constants.ACCOUNT_ADDRESS_PURPOSE_SET;
    //private String site="PRIMARY";
    private String site;

    //account contacts
    private String roleType = Constants.ACCNT_CONTCTS_ROLE_TYPE;
    private String accntCntctsPrimaryIndicator = Constants.ACCNT_CONTCTS_PRIMARY_INDICATOR;
    private String accntCntctsSrcCode;
    private String accntCntctsRelSrc = Constants.CSV_FLAG;

    //contact roles
    private String contctRoleOrigSys = Constants.CSV_FLAG;
    private String contctRoleOrigSysRef;
    private String contctRoleLevel = Constants.N_FLAG;
    private String contctPrimaryRole = Constants.Y_FLAG;
    private String contctRoleTypePrimary = Constants.Y_FLAG;
    private String cntctRelationSrcSys = Constants.CSV_FLAG;

    //Contacts
    private String contactNumber;
    private String deptCode;
    private String deptName;
    private String jobTitle;
    private String jobTitleCode;
    private String relSrcSystem = Constants.CSV_FLAG;

    //Party relationship
    private String subRelPrtyOrigSystem = Constants.CSV_FLAG;
    private String subRelPrtyOrigSystemRef;
    private String relSrcSystemRef;
    private String objRelPrtyOrigSystem = Constants.CSV_FLAG;
    private String relType = Constants.ACCNT_CONTCTS_ROLE_TYPE;
    private String relCode = Constants.RELATIONSHIP_CODE;
    private String subPrtyType;
    private String objPrtyType;

    //customer profiles
    private String custAccntSrcSystem = Constants.CSV_FLAG;
    private String accntSiteSrcSystem;
    private String accntSiteSrcSystemRef;
    private String custProfileClass = Constants.DEFAULT_FLAG;
    private String collectorName = Constants.DEFAULT_COLLECTOR_FLAG;
    private String sendCreditBal = Constants.N_FLAG;
    private String includeInCreditCheck = Constants.N_FLAG;
    private String creditHold = Constants.N_FLAG;
    private String allowDiscount = Constants.N_FLAG;
    private String sendDunningLetters = Constants.N_FLAG;
    private String enableLateCharges = Constants.N_FLAG;
    private String sendStatment = Constants.Y_FLAG;
    private String tolerance = Constants.TOLERANCE;
    private String taxPrintingOption;
    private String accntStatus;
    private String autoCashRuleSet;
    private String creditRating;
    private String discountGraceDays;
    private String interestDaysPeriod;
    private String overrideTerms = Constants.Y_FLAG;
    private String receiptGraceDays;
    private String collectible;
    private String riskCode;
    //private String paymentTerms = "IMMEDIATE";
    private String paymentTerms;
    private String statmentCycle = Constants.MONTHLY;
    private String interestCalcFormula;
    private String groupingRule;
    private String currency = Constants.CURRENCY_SINNGAPORE;
    private String prtySrcSystem = Constants.CSV_FLAG;
    private String autoReciptsIncDisputItems;
    private String receiptClringDays;
    private String orgId;       //bean shell
    private String emptyVal;
    private String customerIdPhone;
    private String customerIdEmail;
    private String customerIdBillToResponsibiltiy;
    private String customerIdStmtResponsibiltiy;

    //location
    private String addLine1;
    private String addLine4;
    private String state;
    private String province;
    //private String county1 = Constants.COUNTRY_SINGAPORE;
    private String county1;
    private String county;
    private String postalCdExtn;
    private String locLang;
    private String description;
    private String shortDesc;
    private String salesTaxGeocode;
    private String salesTaxInCityLmts;
    private String timezoneCdIdent;
    private String addValidSrc;
    private String retAddrValidCode;
    private String addValidDt;
    private String addEffectiveDt  = Constants.CUSTOMER_DATE;
    private String addExpireDt;
    private String validIndicator;
    private String validIneligbleIndicator;
    private String interfaceStatus;
    private String errorIdentifier;
    private String descFlexfieldNumSeg1;
    private String descFlexfieldNumSeg2;
    private String descFlexfieldNumSeg3;
    private String descFlexfieldNumSeg4;
    private String descFlexfieldNumSeg5;
    private String descFlexfieldNumSeg6;
    private String descFlexfieldNumSeg7;
    private String descFlexfieldNumSeg8;
    private String descFlexfieldNumSeg9;
    private String descFlexfieldNumSeg10;
    private String descFlexfieldNumSeg11;
    private String descFlexfieldNumSeg12;
    private String descFlexfieldDtSeg1;
    private String descFlexfieldDtSeg2;
    private String descFlexfieldDtSeg3;
    private String descFlexfieldDtSeg4;
    private String descFlexfieldDtSeg5;
    private String descFlexfieldDtSeg6;
    private String descFlexfieldDtSeg7;
    private String descFlexfieldDtSeg8;
    private String descFlexfieldDtSeg9;
    private String descFlexfieldDtSeg10;
    private String descFlexfieldDtSeg11;
    private String descFlexfieldDtSeg12;
    private String gnrstatus;

    private String end;

    public String getErrorLine(){
        StringBuilder sb = new StringBuilder();
        sb.append(srcSysId).append(Constants.CVS_SEPERATOR)
                .append(customerTypeInput).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(customerName).append(Constants.CVS_SEPERATOR)
                .append(contactPersonName).append(Constants.CVS_SEPERATOR)
                .append(emailAddress).append(Constants.CVS_SEPERATOR)
                .append(contactPhoneNumber).append(Constants.CVS_SEPERATOR)
                .append(billingAddr1).append(Constants.CVS_SEPERATOR)
                .append(billingAddr2).append(Constants.CVS_SEPERATOR)
                //.append(billingAddr3).append(Constants.CVS_SEPERATOR) --> Changed on 23/11 since billing address 3 not in input file
                //.append(billingAddr4).append(Constants.CVS_SEPERATOR)
                .append(city).append(Constants.CVS_SEPERATOR)
                .append(inputState).append(Constants.CVS_SEPERATOR)
                .append(country).append(Constants.CVS_SEPERATOR)
                .append(postalCode).append(Constants.CVS_SEPERATOR)
                .append(effectiveFrom).append(Constants.CVS_SEPERATOR)
                .append(customerStatusInput).append(Constants.CVS_SEPERATOR)
                .append(Constants.ERROR_FLAG).append(Constants.CVS_SEPERATOR)
                .append(errorMsg).append("\r\n");

        return sb.toString();
    }

    public String getOrganizationDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(batchIdentifier).append(Constants.CVS_SEPERATOR)
                .append(prtyOrigSystem).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(insertUpdateIndicator).append(Constants.CVS_SEPERATOR)
                .append(customerType).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CUSTOMER).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append("\"" + customerName + "\"").append(",,,,,,,,,").append(Constants.END_FLAG)
                .append("\r\n");

        return sb.toString();
    }

    public String getPersonDetailsPerson(){
        StringBuilder sb = new StringBuilder();
        sb.append(batchIdentifier).append(Constants.CVS_SEPERATOR)
                .append(prtyOrigSystem).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(insertUpdateIndicator).append(Constants.CVS_SEPERATOR)
                .append(Constants.PERSON).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CUSTOMER).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append("\"" + customerName + "\"").append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,").append(Constants.END_FLAG)
                .append("\r\n");

        return sb.toString();
    }

    public String getPersonDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(batchIdentifier).append(Constants.CVS_SEPERATOR)
                .append(prtyOrigSystem).append(Constants.CVS_SEPERATOR)
                .append(customerId1).append(Constants.CVS_SEPERATOR)
                .append(insertUpdateIndicator).append(Constants.CVS_SEPERATOR)
                .append(Constants.PERSON).append(Constants.CVS_SEPERATOR)
                .append(customerId1).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.ORG_CONTACT).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append("\"" + contactPersonName + "\"").append(Constants.CVS_SEPERATOR)
                .append(contactPersonName1).append(Constants.CVS_SEPERATOR)
                .append(",,,,,").append(Constants.END_FLAG)
                .append("\r\n");

        return sb.toString();
    }

    public String getPhoneDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(batchIdentifier).append(Constants.CVS_SEPERATOR)
                .append(Constants.CSV_FLAG).append(Constants.CVS_SEPERATOR)
                .append(customerIdPhone).append(Constants.CVS_SEPERATOR)
                .append(prtyOrigSystem).append(Constants.CVS_SEPERATOR)
                .append(customerId1).append(Constants.CVS_SEPERATOR)
                .append(prtySiteOrgSystem).append(Constants.CVS_SEPERATOR)
                .append(customerId).append(Constants.CVS_SEPERATOR)
                .append(primaryIndicator).append(Constants.CVS_SEPERATOR)
                .append(insertUpdateIndicator).append(Constants.CVS_SEPERATOR)
                .append(Constants.PHONE).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.SINGAPORE_PHONE_CODE).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.PHONE_LINE_TYPE).append(Constants.CVS_SEPERATOR)
                .append(contactPhoneNumber).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(fromDt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,")
                .append(Constants.CSV_FLAG).append(Constants.CVS_SEPERATOR)
                .append(customerId1).append(Constants.CVS_SEPERATOR).append(Constants.END_FLAG)
                .append("\r\n");

        return sb.toString();
    }

    public String getEmailDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(batchIdentifier).append(Constants.CVS_SEPERATOR)
                .append(Constants.CSV_FLAG).append(Constants.CVS_SEPERATOR)
                .append(customerIdEmail).append(Constants.CVS_SEPERATOR)
                .append(prtyOrigSystem).append(Constants.CVS_SEPERATOR)
                .append(customerId1).append(Constants.CVS_SEPERATOR)
                //.append(prtySiteOrgSystem).append(Constants.CVS_SEPERATOR)
                //.append(customerId).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(primaryIndicator).append(Constants.CVS_SEPERATOR)
                .append(insertUpdateIndicator).append(Constants.CVS_SEPERATOR)
                .append(Constants.EMAIL).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(mappedEmailAddr).append(Constants.CVS_SEPERATOR)
                .append(Constants.EMAIL_FORMAT).append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,")
                .append(fromDt).append(Constants.CVS_SEPERATOR)
                .append(Constants.CVS_SEPERATOR)
                .append(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,")
                .append(Constants.CSV_FLAG).append(Constants.CVS_SEPERATOR)
                .append(customerId1).append(Constants.CVS_SEPERATOR).append(Constants.END_FLAG)
                .append("\r\n");

        return sb.toString();
    }

    public String getAccountBillToDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(batchIdentifier).append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR)
                .append(insertUpdateIndicator).append(Constants.CVS_SEPERATOR)
                .append(Constants.CSV_FLAG).append(Constants.CVS_SEPERATOR)
                .append(customerId1).append(Constants.CVS_SEPERATOR)
                .append(Constants.CSV_FLAG).append(Constants.CVS_SEPERATOR)
                .append(customerIdBillToResponsibiltiy).append(Constants.CVS_SEPERATOR)
                .append(Constants.BILLL_TO).append(Constants.CVS_SEPERATOR)
                .append(primaryIndicator).append(Constants.CVS_SEPERATOR).append(Constants.END_FLAG)
                .append("\r\n");

        return sb.toString();
    }

    public String getAccountStmtDetails(){
        StringBuilder sb = new StringBuilder();
        sb.append(batchIdentifier).append(Constants.CVS_SEPERATOR).append(Constants.CVS_SEPERATOR)
                .append(insertUpdateIndicator).append(Constants.CVS_SEPERATOR)
                .append(Constants.CSV_FLAG).append(Constants.CVS_SEPERATOR)
                .append(customerId1).append(Constants.CVS_SEPERATOR)
                .append(Constants.CSV_FLAG).append(Constants.CVS_SEPERATOR)
                .append(customerIdStmtResponsibiltiy).append(Constants.CVS_SEPERATOR)
                .append(Constants.STMTS).append(Constants.CVS_SEPERATOR)
                .append(primaryIndicator).append(Constants.CVS_SEPERATOR).append(Constants.END_FLAG)
                .append("\r\n");

        return sb.toString();
    }

    public String getCustomerType() {
        return customerType;
    }
    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }
    public String getCustomerId() {
        return customerId;
    }
    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }
    public String getCustomerName() {
        return customerName;
    }
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }
    public String getContactPersonName() {
        return contactPersonName;
    }
    public void setContactPersonName(String contactPersonName) {
        this.contactPersonName = contactPersonName;
    }
    public String getEmailAddress() {
        return emailAddress;
    }
    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
    public String getContactPhoneNumber() {
        return contactPhoneNumber;
    }
    public void setContactPhoneNumber(String contactPhoneNumber) {
        this.contactPhoneNumber = contactPhoneNumber;
    }
    public String getBillingAddr1() {
        return billingAddr1;
    }
    public void setBillingAddr1(String billingAddr1) {
        this.billingAddr1 = billingAddr1;
    }
    public String getBillingAddr2() {
        return billingAddr2;
    }
    public void setBillingAddr2(String billingAddr2) {
        this.billingAddr2 = billingAddr2;
    }
    public String getBillingAddr3() {
        return billingAddr3;
    }
    public void setBillingAddr3(String billingAddr3) {
        this.billingAddr3 = billingAddr3;
    }
    public String getBillingAddr4() {
        return billingAddr4;
    }
    public void setBillingAddr4(String billingAddr4) {
        this.billingAddr4 = billingAddr4;
    }
    public String getCity() {
        return city;
    }
    public void setCity(String city) {
        this.city = city;
    }
    public String getInputState() {
        return inputState;
    }
    public void setInputState(String inputState) {
        this.inputState = inputState;
    }
    public String getCountry() {
        return country;
    }
    public void setCountry(String country) {
        this.country = country;
    }
    public String getPostalCode() {
        return postalCode;
    }
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }
    public String getEffectiveFrom() {
        return effectiveFrom;
    }
    public void setEffectiveFrom(String effectiveFrom) {
        this.effectiveFrom = effectiveFrom;
    }
    public String getCustomerStatus() {
        return customerStatus;
    }
    public void setCustomerStatus(String customerStatus) {
        this.customerStatus = customerStatus;
    }
    public String getBatchIdentifier() {
        return batchIdentifier;
    }
    public void setBatchIdentifier(String batchIdentifier) {
        this.batchIdentifier = batchIdentifier;
    }
    public String getPrtyOrigSystem() {
        return prtyOrigSystem;
    }
    public void setPrtyOrigSystem(String prtyOrigSystem) {
        this.prtyOrigSystem = prtyOrigSystem;
    }
    public String getPartyUsageCode() {
        return partyUsageCode;
    }

    public void setPartyUsageCode(String partyUsageCode) {
        this.partyUsageCode = partyUsageCode;
    }
    public String getInsertUpdateIndicator() {
        return insertUpdateIndicator;
    }
    public void setInsertUpdateIndicator(String insertUpdateIndicator) {
        this.insertUpdateIndicator = insertUpdateIndicator;
    }
    public String getPrtyNumber() {
        return prtyNumber;
    }
    public void setPrtyNumber(String prtyNumber) {
        this.prtyNumber = prtyNumber;
    }
    public String getSalutation() {
        return salutation;
    }
    public void setSalutation(String salutation) {
        this.salutation = salutation;
    }
    public String getTaxPayer() {
        return taxPayer;
    }
    public void setTaxPayer(String taxPayer) {
        this.taxPayer = taxPayer;
    }
    public String getDunsNum() {
        return dunsNum;
    }
    public void setDunsNum(String dunsNum) {
        this.dunsNum = dunsNum;
    }
    public String getPersonFrstNm() {
        return personFrstNm;
    }
    public void setPersonFrstNm(String personFrstNm) {
        this.personFrstNm = personFrstNm;
    }
    public String getPersonLstNm() {
        return personLstNm;
    }
    public void setPersonLstNm(String personLstNm) {
        this.personLstNm = personLstNm;
    }
    public String getPersonLstNmPrefix() {
        return personLstNmPrefix;
    }
    public void setPersonLstNmPrefix(String personLstNmPrefix) {
        this.personLstNmPrefix = personLstNmPrefix;
    }
    public String getPersonSecndLstNm() {
        return personSecndLstNm;
    }
    public void setPersonSecndLstNm(String personSecndLstNm) {
        this.personSecndLstNm = personSecndLstNm;
    }
    public String getPersonMidlNm() {
        return personMidlNm;
    }
    public void setPersonMidlNm(String personMidlNm) {
        this.personMidlNm = personMidlNm;
    }
    public String getPersonNmSuffix() {
        return personNmSuffix;
    }
    public void setPersonNmSuffix(String personNmSuffix) {
        this.personNmSuffix = personNmSuffix;
    }
    public String getPersonTitle() {
        return personTitle;
    }
    public void setPersonTitle(String personTitle) {
        this.personTitle = personTitle;
    }
    public String getDescFixfieldSeg() {
        return descFixfieldSeg;
    }
    public void setDescFixfieldSeg(String descFixfieldSeg) {
        this.descFixfieldSeg = descFixfieldSeg;
    }
    public String getPrtySiteOrgSystem() {
        return prtySiteOrgSystem;
    }
    public void setPrtySiteOrgSystem(String prtySiteOrgSystem) {
        this.prtySiteOrgSystem = prtySiteOrgSystem;
    }
    public String getLocOriginalSystem() {
        return locOriginalSystem;
    }
    public void setLocOriginalSystem(String locOriginalSystem) {
        this.locOriginalSystem = locOriginalSystem;
    }
    public String getPrtySiteFromDt() {
        return prtySiteFromDt;
    }
    public void setPrtySiteFromDt(String prtySiteFromDt) {
        this.prtySiteFromDt = prtySiteFromDt;
    }
    public String getPrtySiteNum() {
        return prtySiteNum;
    }
    public void setPrtySiteNum(String prtySiteNum) {
        this.prtySiteNum = prtySiteNum;
    }
    public String getPrtySiteToDt() {
        return prtySiteToDt;
    }
    public void setPrtySiteToDt(String prtySiteToDt) {
        this.prtySiteToDt = prtySiteToDt;
    }
    public String getMailStop() {
        return mailStop;
    }
    public void setMailStop(String mailStop) {
        this.mailStop = mailStop;
    }
    public String getIdentifyingAddr() {
        return identifyingAddr;
    }
    public void setIdentifyingAddr(String identifyingAddr) {
        this.identifyingAddr = identifyingAddr;
    }
    public String getSiteLanguage() {
        return siteLanguage;
    }
    public void setSiteLanguage(String siteLanguage) {
        this.siteLanguage = siteLanguage;
    }
    public String getRelationSrcSys() {
        return relationSrcSys;
    }
    public void setRelationSrcSys(String relationSrcSys) {
        this.relationSrcSys = relationSrcSys;
    }
    public String getRelationSrcSysRef() {
        return relationSrcSysRef;
    }
    public void setRelationSrcSysRef(String relationSrcSysRef) {
        this.relationSrcSysRef = relationSrcSysRef;
    }
    public String getPrtSiteUseType() {
        return prtSiteUseType;
    }
    public void setPrtSiteUseType(String prtSiteUseType) {
        this.prtSiteUseType = prtSiteUseType;
    }
    public String getPrimaryIndicator() {
        return primaryIndicator;
    }
    public void setPrimaryIndicator(String primaryIndicator) {
        this.primaryIndicator = primaryIndicator;
    }
    public String getFromDt() {
        return fromDt;
    }
    public void setFromDt(String fromDt) {
        this.fromDt = fromDt;
    }
    public String getToDt() {
        return toDt;
    }
    public void setToDt(String toDt) {
        this.toDt = toDt;
    }
    public String getPrtySiteUseOrgSys() {
        return prtySiteUseOrgSys;
    }
    public void setPrtySiteUseOrgSys(String prtySiteUseOrgSys) {
        this.prtySiteUseOrgSys = prtySiteUseOrgSys;
    }
    public String getCustAccntSrcSys() {
        return custAccntSrcSys;
    }
    public void setCustAccntSrcSys(String custAccntSrcSys) {
        this.custAccntSrcSys = custAccntSrcSys;
    }
    public String getAccntNum() {
        return accntNum;
    }
    public void setAccntNum(String accntNum) {
        this.accntNum = accntNum;
    }
    public String getAccntType() {
        return accntType;
    }
    public void setAccntType(String accntType) {
        this.accntType = accntType;
    }
    public String getCustClass() {
        return custClass;
    }
    public void setCustClass(String custClass) {
        this.custClass = custClass;
    }
    public String getAccntDesc() {
        return accntDesc;
    }
    public void setAccntDesc(String accntDesc) {
        this.accntDesc = accntDesc;
    }
    public String getAccntEstDt() {
        return accntEstDt;
    }
    public void setAccntEstDt(String accntEstDt) {
        this.accntEstDt = accntEstDt;
    }
    public String getUserDefcontxtPrompt() {
        return userDefcontxtPrompt;
    }
    public void setUserDefcontxtPrompt(String userDefcontxtPrompt) {
        this.userDefcontxtPrompt = userDefcontxtPrompt;
    }
    public String getDescrFlexfieldSeg1() {
        return descrFlexfieldSeg1;
    }
    public void setDescrFlexfieldSeg1(String descrFlexfieldSeg1) {
        this.descrFlexfieldSeg1 = descrFlexfieldSeg1;
    }
    public String getDescrFlexfieldSeg2() {
        return descrFlexfieldSeg2;
    }
    public void setDescrFlexfieldSeg2(String descrFlexfieldSeg2) {
        this.descrFlexfieldSeg2 = descrFlexfieldSeg2;
    }
    public String getDescrFlexfieldSeg3() {
        return descrFlexfieldSeg3;
    }
    public void setDescrFlexfieldSeg3(String descrFlexfieldSeg3) {
        this.descrFlexfieldSeg3 = descrFlexfieldSeg3;
    }
    public String getDescrFlexfieldSeg4() {
        return descrFlexfieldSeg4;
    }
    public void setDescrFlexfieldSeg4(String descrFlexfieldSeg4) {
        this.descrFlexfieldSeg4 = descrFlexfieldSeg4;
    }
    public String getDescrFlexfieldSeg5() {
        return descrFlexfieldSeg5;
    }
    public void setDescrFlexfieldSeg5(String descrFlexfieldSeg5) {
        this.descrFlexfieldSeg5 = descrFlexfieldSeg5;
    }
    public String getDescrFlexfieldSeg6() {
        return descrFlexfieldSeg6;
    }
    public void setDescrFlexfieldSeg6(String descrFlexfieldSeg6) {
        this.descrFlexfieldSeg6 = descrFlexfieldSeg6;
    }
    public String getDescrFlexfieldSeg7() {
        return descrFlexfieldSeg7;
    }
    public void setDescrFlexfieldSeg7(String descrFlexfieldSeg7) {
        this.descrFlexfieldSeg7 = descrFlexfieldSeg7;
    }
    public String getDescrFlexfieldSeg8() {
        return descrFlexfieldSeg8;
    }
    public void setDescrFlexfieldSeg8(String descrFlexfieldSeg8) {
        this.descrFlexfieldSeg8 = descrFlexfieldSeg8;
    }
    public String getDescrFlexfieldSeg9() {
        return descrFlexfieldSeg9;
    }
    public void setDescrFlexfieldSeg9(String descrFlexfieldSeg9) {
        this.descrFlexfieldSeg9 = descrFlexfieldSeg9;
    }
    public String getDescrFlexfieldSeg10() {
        return descrFlexfieldSeg10;
    }
    public void setDescrFlexfieldSeg10(String descrFlexfieldSeg10) {
        this.descrFlexfieldSeg10 = descrFlexfieldSeg10;
    }
    public String getDescrFlexfieldSeg11() {
        return descrFlexfieldSeg11;
    }
    public void setDescrFlexfieldSeg11(String descrFlexfieldSeg11) {
        this.descrFlexfieldSeg11 = descrFlexfieldSeg11;
    }
    public String getDescrFlexfieldSeg12() {
        return descrFlexfieldSeg12;
    }
    public void setDescrFlexfieldSeg12(String descrFlexfieldSeg12) {
        this.descrFlexfieldSeg12 = descrFlexfieldSeg12;
    }
    public String getDescrFlexfieldSeg13() {
        return descrFlexfieldSeg13;
    }
    public void setDescrFlexfieldSeg13(String descrFlexfieldSeg13) {
        this.descrFlexfieldSeg13 = descrFlexfieldSeg13;
    }
    public String getDescrFlexfieldSeg14() {
        return descrFlexfieldSeg14;
    }
    public void setDescrFlexfieldSeg14(String descrFlexfieldSeg14) {
        this.descrFlexfieldSeg14 = descrFlexfieldSeg14;
    }
    public String getDescrFlexfieldSeg15() {
        return descrFlexfieldSeg15;
    }
    public void setDescrFlexfieldSeg15(String descrFlexfieldSeg15) {
        this.descrFlexfieldSeg15 = descrFlexfieldSeg15;
    }
    public String getDescrFlexfieldSeg16() {
        return descrFlexfieldSeg16;
    }
    public void setDescrFlexfieldSeg16(String descrFlexfieldSeg16) {
        this.descrFlexfieldSeg16 = descrFlexfieldSeg16;
    }
    public String getDescrFlexfieldSeg17() {
        return descrFlexfieldSeg17;
    }
    public void setDescrFlexfieldSeg17(String descrFlexfieldSeg17) {
        this.descrFlexfieldSeg17 = descrFlexfieldSeg17;
    }
    public String getDescrFlexfieldSeg18() {
        return descrFlexfieldSeg18;
    }
    public void setDescrFlexfieldSeg18(String descrFlexfieldSeg18) {
        this.descrFlexfieldSeg18 = descrFlexfieldSeg18;
    }
    public String getDescrFlexfieldSeg19() {
        return descrFlexfieldSeg19;
    }
    public void setDescrFlexfieldSeg19(String descrFlexfieldSeg19) {
        this.descrFlexfieldSeg19 = descrFlexfieldSeg19;
    }
    public String getDescrFlexfieldSeg20() {
        return descrFlexfieldSeg20;
    }
    public void setDescrFlexfieldSeg20(String descrFlexfieldSeg20) {
        this.descrFlexfieldSeg20 = descrFlexfieldSeg20;
    }
    public String getDescrFlexfieldSeg21() {
        return descrFlexfieldSeg21;
    }
    public void setDescrFlexfieldSeg21(String descrFlexfieldSeg21) {
        this.descrFlexfieldSeg21 = descrFlexfieldSeg21;
    }
    public String getDescrFlexfieldSeg22() {
        return descrFlexfieldSeg22;
    }
    public void setDescrFlexfieldSeg22(String descrFlexfieldSeg22) {
        this.descrFlexfieldSeg22 = descrFlexfieldSeg22;
    }
    public String getDescrFlexfieldSeg23() {
        return descrFlexfieldSeg23;
    }
    public void setDescrFlexfieldSeg23(String descrFlexfieldSeg23) {
        this.descrFlexfieldSeg23 = descrFlexfieldSeg23;
    }
    public String getDescrFlexfieldSeg24() {
        return descrFlexfieldSeg24;
    }
    public void setDescrFlexfieldSeg24(String descrFlexfieldSeg24) {
        this.descrFlexfieldSeg24 = descrFlexfieldSeg24;
    }
    public String getDescrFlexfieldSeg25() {
        return descrFlexfieldSeg25;
    }
    public void setDescrFlexfieldSeg25(String descrFlexfieldSeg25) {
        this.descrFlexfieldSeg25 = descrFlexfieldSeg25;
    }
    public String getDescrFlexfieldSeg26() {
        return descrFlexfieldSeg26;
    }
    public void setDescrFlexfieldSeg26(String descrFlexfieldSeg26) {
        this.descrFlexfieldSeg26 = descrFlexfieldSeg26;
    }
    public String getDescrFlexfieldSeg27() {
        return descrFlexfieldSeg27;
    }
    public void setDescrFlexfieldSeg27(String descrFlexfieldSeg27) {
        this.descrFlexfieldSeg27 = descrFlexfieldSeg27;
    }
    public String getDescrFlexfieldSeg28() {
        return descrFlexfieldSeg28;
    }
    public void setDescrFlexfieldSeg28(String descrFlexfieldSeg28) {
        this.descrFlexfieldSeg28 = descrFlexfieldSeg28;
    }
    public String getDescrFlexfieldSeg29() {
        return descrFlexfieldSeg29;
    }
    public void setDescrFlexfieldSeg29(String descrFlexfieldSeg29) {
        this.descrFlexfieldSeg29 = descrFlexfieldSeg29;
    }
    public String getDescrFlexfieldSeg30() {
        return descrFlexfieldSeg30;
    }
    public void setDescrFlexfieldSeg30(String descrFlexfieldSeg30) {
        this.descrFlexfieldSeg30 = descrFlexfieldSeg30;
    }
    public String getAccntTerminationDt() {
        return accntTerminationDt;
    }
    public void setAccntTerminationDt(String accntTerminationDt) {
        this.accntTerminationDt = accntTerminationDt;
    }
    public String getAccntSiteSrcSys() {
        return accntSiteSrcSys;
    }
    public void setAccntSiteSrcSys(String accntSiteSrcSys) {
        this.accntSiteSrcSys = accntSiteSrcSys;
    }
    public String getCustCategoryCd() {
        return custCategoryCd;
    }
    public void setCustCategoryCd(String custCategoryCd) {
        this.custCategoryCd = custCategoryCd;
    }
    public String getAccntSiteSrcSysRef() {
        return accntSiteSrcSysRef;
    }

    public void setAccntSiteSrcSysRef(String accntSiteSrcSysRef) {
        this.accntSiteSrcSysRef = accntSiteSrcSysRef;
    }
    public String getTransalatedCustNm() {
        return transalatedCustNm;
    }
    public void setTransalatedCustNm(String transalatedCustNm) {
        this.transalatedCustNm = transalatedCustNm;
    }
    public String getAccntAddrSet() {
        return accntAddrSet;
    }
    public void setAccntAddrSet(String accntAddrSet) {
        this.accntAddrSet = accntAddrSet;
    }
    public String getKeyAccnt() {
        return keyAccnt;
    }
    public void setKeyAccnt(String keyAccnt) {
        this.keyAccnt = keyAccnt;
    }
    public String getAccntSitePurposeSrcSys() {
        return accntSitePurposeSrcSys;
    }
    public void setAccntSitePurposeSrcSys(String accntSitePurposeSrcSys) {
        this.accntSitePurposeSrcSys = accntSitePurposeSrcSys;
    }
    public String getAccntSitePurposeSrcSysRef() {
        return accntSitePurposeSrcSysRef;
    }
    public void setAccntSitePurposeSrcSysRef(String accntSitePurposeSrcSysRef) {
        this.accntSitePurposeSrcSysRef = accntSitePurposeSrcSysRef;
    }
    public String getPurpose() {
        return purpose;
    }
    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }
    public String getPurposeFromDt() {
        return purposeFromDt;
    }
    public void setPurposeFromDt(String purposeFromDt) {
        this.purposeFromDt = purposeFromDt;
    }
    public String getPurposeToDt() {
        return purposeToDt;
    }
    public void setPurposeToDt(String purposeToDt) {
        this.purposeToDt = purposeToDt;
    }
    public String getAccntAddPurposeSet() {
        return accntAddPurposeSet;
    }
    public void setAccntAddPurposeSet(String accntAddPurposeSet) {
        this.accntAddPurposeSet = accntAddPurposeSet;
    }
    public String getSite() {
        return site;
    }
    public void setSite(String site) {
        this.site = site;
    }
    public String getRoleType() {
        return roleType;
    }
    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
    public String getAccntCntctsPrimaryIndicator() {
        return accntCntctsPrimaryIndicator;
    }
    public void setAccntCntctsPrimaryIndicator(String accntCntctsPrimaryIndicator) {
        this.accntCntctsPrimaryIndicator = accntCntctsPrimaryIndicator;
    }
    public String getAccntCntctsSrcCode() {
        return accntCntctsSrcCode;
    }
    public void setAccntCntctsSrcCode(String accntCntctsSrcCode) {
        this.accntCntctsSrcCode = accntCntctsSrcCode;
    }
    public String getAccntCntctsRelSrc() {
        return accntCntctsRelSrc;
    }
    public void setAccntCntctsRelSrc(String accntCntctsRelSrc) {
        this.accntCntctsRelSrc = accntCntctsRelSrc;
    }
    public String getContctRoleOrigSys() {
        return contctRoleOrigSys;
    }
    public void setContctRoleOrigSys(String contctRoleOrigSys) {
        this.contctRoleOrigSys = contctRoleOrigSys;
    }
    public String getContctRoleOrigSysRef() {
        return contctRoleOrigSysRef;
    }

    public void setContctRoleOrigSysRef(String contctRoleOrigSysRef) {
        this.contctRoleOrigSysRef = contctRoleOrigSysRef;
    }
    public String getContctRoleLevel() {
        return contctRoleLevel;
    }
    public void setContctRoleLevel(String contctRoleLevel) {
        this.contctRoleLevel = contctRoleLevel;
    }
    public String getContctPrimaryRole() {
        return contctPrimaryRole;
    }
    public void setContctPrimaryRole(String contctPrimaryRole) {
        this.contctPrimaryRole = contctPrimaryRole;
    }
    public String getContctRoleTypePrimary() {
        return contctRoleTypePrimary;
    }
    public void setContctRoleTypePrimary(String contctRoleTypePrimary) {
        this.contctRoleTypePrimary = contctRoleTypePrimary;
    }
    public String getCntctRelationSrcSys() {
        return cntctRelationSrcSys;
    }

    public void setCntctRelationSrcSys(String cntctRelationSrcSys) {
        this.cntctRelationSrcSys = cntctRelationSrcSys;
    }
    public String getContactNumber() {
        return contactNumber;
    }
    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }
    public String getDeptCode() {
        return deptCode;
    }
    public void setDeptCode(String deptCode) {
        this.deptCode = deptCode;
    }
    public String getDeptName() {
        return deptName;
    }
    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
    public String getJobTitle() {
        return jobTitle;
    }
    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }
    public String getJobTitleCode() {
        return jobTitleCode;
    }
    public void setJobTitleCode(String jobTitleCode) {
        this.jobTitleCode = jobTitleCode;
    }
    public String getRelSrcSystem() {
        return relSrcSystem;
    }

    public void setRelSrcSystem(String relSrcSystem) {
        this.relSrcSystem = relSrcSystem;
    }
    public String getSubRelPrtyOrigSystem() {
        return subRelPrtyOrigSystem;
    }
    public String getSubRelPrtyOrigSystemRef() {
        return subRelPrtyOrigSystemRef;
    }
    public void setSubRelPrtyOrigSystemRef(String subRelPrtyOrigSystemRef) {
        this.subRelPrtyOrigSystemRef = subRelPrtyOrigSystemRef;
    }
    public String getRelSrcSystemRef() {
        return relSrcSystemRef;
    }
    public void setRelSrcSystemRef(String relSrcSystemRef) {
        this.relSrcSystemRef = relSrcSystemRef;
    }
    public void setSubRelPrtyOrigSystem(String subRelPrtyOrigSystem) {
        this.subRelPrtyOrigSystem = subRelPrtyOrigSystem;
    }
    public String getObjRelPrtyOrigSystem() {
        return objRelPrtyOrigSystem;
    }
    public void setObjRelPrtyOrigSystem(String objRelPrtyOrigSystem) {
        this.objRelPrtyOrigSystem = objRelPrtyOrigSystem;
    }
    public String getRelType() {
        return relType;
    }
    public void setRelType(String relType) {
        this.relType = relType;
    }
    public String getRelCode() {
        return relCode;
    }
    public void setRelCode(String relCode) {
        this.relCode = relCode;
    }
    public String getSubPrtyType() {
        return subPrtyType;
    }
    public void setSubPrtyType(String subPrtyType) {
        this.subPrtyType = subPrtyType;
    }
    public String getObjPrtyType() {
        return objPrtyType;
    }
    public void setObjPrtyType(String objPrtyType) {
        this.objPrtyType = objPrtyType;
    }
    public String getCustomerId1() {
        return customerId1;
    }
    public void setCustomerId1(String customerId1) {
        this.customerId1 = customerId1;
    }
    public String getCustAccntSrcSystem() {
        return custAccntSrcSystem;
    }
    public void setCustAccntSrcSystem(String custAccntSrcSystem) {
        this.custAccntSrcSystem = custAccntSrcSystem;
    }
    public String getAccntSiteSrcSystem() {
        return accntSiteSrcSystem;
    }
    public void setAccntSiteSrcSystem(String accntSiteSrcSystem) {
        this.accntSiteSrcSystem = accntSiteSrcSystem;
    }
    public String getAccntSiteSrcSystemRef() {
        return accntSiteSrcSystemRef;
    }
    public void setAccntSiteSrcSystemRef(String accntSiteSrcSystemRef) {
        this.accntSiteSrcSystemRef = accntSiteSrcSystemRef;
    }
    public String getCustProfileClass() {
        return custProfileClass;
    }
    public void setCustProfileClass(String custProfileClass) {
        this.custProfileClass = custProfileClass;
    }
    public String getCollectorName() {
        return collectorName;
    }
    public void setCollectorName(String collectorName) {
        this.collectorName = collectorName;
    }
    public String getSendCreditBal() {
        return sendCreditBal;
    }
    public void setSendCreditBal(String sendCreditBal) {
        this.sendCreditBal = sendCreditBal;
    }
    public String getIncludeInCreditCheck() {
        return includeInCreditCheck;
    }
    public void setIncludeInCreditCheck(String includeInCreditCheck) {
        this.includeInCreditCheck = includeInCreditCheck;
    }
    public String getCreditHold() {
        return creditHold;
    }
    public void setCreditHold(String creditHold) {
        this.creditHold = creditHold;
    }
    public String getAllowDiscount() {
        return allowDiscount;
    }
    public void setAllowDiscount(String allowDiscount) {
        this.allowDiscount = allowDiscount;
    }
    public String getSendDunningLetters() {
        return sendDunningLetters;
    }
    public void setSendDunningLetters(String sendDunningLetters) {
        this.sendDunningLetters = sendDunningLetters;
    }
    public String getEnableLateCharges() {
        return enableLateCharges;
    }
    public void setEnableLateCharges(String enableLateCharges) {
        this.enableLateCharges = enableLateCharges;
    }
    public String getSendStatment() {
        return sendStatment;
    }
    public void setSendStatment(String sendStatment) {
        this.sendStatment = sendStatment;
    }
    public String getTolerance() {
        return tolerance;
    }
    public void setTolerance(String tolerance) {
        this.tolerance = tolerance;
    }
    public String getTaxPrintingOption() {
        return taxPrintingOption;
    }
    public void setTaxPrintingOption(String taxPrintingOption) {
        this.taxPrintingOption = taxPrintingOption;
    }
    public String getAccntStatus() {
        return accntStatus;
    }
    public void setAccntStatus(String accntStatus) {
        this.accntStatus = accntStatus;
    }
    public String getAutoCashRuleSet() {
        return autoCashRuleSet;
    }
    public void setAutoCashRuleSet(String autoCashRuleSet) {
        this.autoCashRuleSet = autoCashRuleSet;
    }
    public String getCreditRating() {
        return creditRating;
    }
    public void setCreditRating(String creditRating) {
        this.creditRating = creditRating;
    }
    public String getDiscountGraceDays() {
        return discountGraceDays;
    }
    public void setDiscountGraceDays(String discountGraceDays) {
        this.discountGraceDays = discountGraceDays;
    }
    public String getInterestDaysPeriod() {
        return interestDaysPeriod;
    }
    public void setInterestDaysPeriod(String interestDaysPeriod) {
        this.interestDaysPeriod = interestDaysPeriod;
    }
    public String getOverrideTerms() {
        return overrideTerms;
    }
    public void setOverrideTerms(String overrideTerms) {
        this.overrideTerms = overrideTerms;
    }
    public String getReceiptGraceDays() {
        return receiptGraceDays;
    }
    public void setReceiptGraceDays(String receiptGraceDays) {
        this.receiptGraceDays = receiptGraceDays;
    }
    public String getCollectible() {
        return collectible;
    }
    public void setCollectible(String collectible) {
        this.collectible = collectible;
    }
    public String getRiskCode() {
        return riskCode;
    }
    public void setRiskCode(String riskCode) {
        this.riskCode = riskCode;
    }
    public String getPaymentTerms() {
        return paymentTerms;
    }
    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }
    public String getStatmentCycle() {
        return statmentCycle;
    }
    public void setStatmentCycle(String statmentCycle) {
        this.statmentCycle = statmentCycle;
    }
    public String getInterestCalcFormula() {
        return interestCalcFormula;
    }
    public void setInterestCalcFormula(String interestCalcFormula) {
        this.interestCalcFormula = interestCalcFormula;
    }
    public String getGroupingRule() {
        return groupingRule;
    }
    public void setGroupingRule(String groupingRule) {
        this.groupingRule = groupingRule;
    }
    public String getCurrency() {
        return currency;
    }
    public void setCurrency(String currency) {
        this.currency = currency;
    }
    public String getPrtySrcSystem() {
        return prtySrcSystem;
    }
    public void setPrtySrcSystem(String prtySrcSystem) {
        this.prtySrcSystem = prtySrcSystem;
    }
    public String getAutoReciptsIncDisputItems() {
        return autoReciptsIncDisputItems;
    }
    public void setAutoReciptsIncDisputItems(String autoReciptsIncDisputItems) {
        this.autoReciptsIncDisputItems = autoReciptsIncDisputItems;
    }
    public String getReceiptClringDays() {
        return receiptClringDays;
    }
    public void setReceiptClringDays(String receiptClringDays) {
        this.receiptClringDays = receiptClringDays;
    }
    public String getOrgId() {
        return orgId;
    }
    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    public String getEmptyVal() {
        return emptyVal;
    }
    public void setEmptyVal(String emptyVal) {
        this.emptyVal = emptyVal;
    }
    public String getCustomerIdPhone() {
        return customerIdPhone;
    }
    public void setCustomerIdPhone(String customerIdPhone) {
        this.customerIdPhone = customerIdPhone;
    }
    public String getCustomerIdEmail() {
        return customerIdEmail;
    }
    public void setCustomerIdEmail(String customerIdEmail) {
        this.customerIdEmail = customerIdEmail;
    }
    public String getCustomerIdBillToResponsibiltiy() {
        return customerIdBillToResponsibiltiy;
    }
    public void setCustomerIdBillToResponsibiltiy(String customerIdBillToResponsibiltiy) {
        this.customerIdBillToResponsibiltiy = customerIdBillToResponsibiltiy;
    }
    public String getCustomerIdStmtResponsibiltiy() {
        return customerIdStmtResponsibiltiy;
    }
    public void setCustomerIdStmtResponsibiltiy(String customerIdStmtResponsibiltiy) {
        this.customerIdStmtResponsibiltiy = customerIdStmtResponsibiltiy;
    }
    public String getAddLine4() {
        return addLine4;
    }
    public void setAddLine4(String addLine4) {
        this.addLine4 = addLine4;
    }
    public String getAddLine1() {
        return addLine1;
    }

    public void setAddLine1(String addLine1) {
        this.addLine1 = addLine1;
    }
    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
    public String getProvince() {
        return province;
    }
    public void setProvince(String province) {
        this.province = province;
    }
    public String getCounty() {
        return county;
    }
    public String getCounty1() {
        return county1;
    }
    public void setCounty1(String county1) {
        this.county1 = county1;
    }
    public void setCounty(String county) {
        this.county = county;
    }
    public String getPostalCdExtn() {
        return postalCdExtn;
    }
    public void setPostalCdExtn(String postalCdExtn) {
        this.postalCdExtn = postalCdExtn;
    }
    public String getLocLang() {
        return locLang;
    }
    public void setLocLang(String locLang) {
        this.locLang = locLang;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getShortDesc() {
        return shortDesc;
    }
    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }
    public String getSalesTaxGeocode() {
        return salesTaxGeocode;
    }
    public void setSalesTaxGeocode(String salesTaxGeocode) {
        this.salesTaxGeocode = salesTaxGeocode;
    }
    public String getSalesTaxInCityLmts() {
        return salesTaxInCityLmts;
    }
    public void setSalesTaxInCityLmts(String salesTaxInCityLmts) {
        this.salesTaxInCityLmts = salesTaxInCityLmts;
    }
    public String getTimezoneCdIdent() {
        return timezoneCdIdent;
    }
    public void setTimezoneCdIdent(String timezoneCdIdent) {
        this.timezoneCdIdent = timezoneCdIdent;
    }
    public String getAddValidSrc() {
        return addValidSrc;
    }
    public void setAddValidSrc(String addValidSrc) {
        this.addValidSrc = addValidSrc;
    }
    public String getRetAddrValidCode() {
        return retAddrValidCode;
    }
    public void setRetAddrValidCode(String retAddrValidCode) {
        this.retAddrValidCode = retAddrValidCode;
    }
    public String getAddValidDt() {
        return addValidDt;
    }
    public void setAddValidDt(String addValidDt) {
        this.addValidDt = addValidDt;
    }
    public String getAddEffectiveDt() {
        return addEffectiveDt;
    }
    public void setAddEffectiveDt(String addEffectiveDt) {
        this.addEffectiveDt = addEffectiveDt;
    }
    public String getAddExpireDt() {
        return addExpireDt;
    }
    public void setAddExpireDt(String addExpireDt) {
        this.addExpireDt = addExpireDt;
    }
    public String getValidIndicator() {
        return validIndicator;
    }
    public void setValidIndicator(String validIndicator) {
        this.validIndicator = validIndicator;
    }
    public String getValidIneligbleIndicator() {
        return validIneligbleIndicator;
    }
    public void setValidIneligbleIndicator(String validIneligbleIndicator) {
        this.validIneligbleIndicator = validIneligbleIndicator;
    }
    public String getInterfaceStatus() {
        return interfaceStatus;
    }
    public void setInterfaceStatus(String interfaceStatus) {
        this.interfaceStatus = interfaceStatus;
    }
    public String getErrorIdentifier() {
        return errorIdentifier;
    }
    public void setErrorIdentifier(String errorIdentifier) {
        this.errorIdentifier = errorIdentifier;
    }
    public String getDescFlexfieldNumSeg1() {
        return descFlexfieldNumSeg1;
    }
    public void setDescFlexfieldNumSeg1(String descFlexfieldNumSeg1) {
        this.descFlexfieldNumSeg1 = descFlexfieldNumSeg1;
    }
    public String getDescFlexfieldNumSeg2() {
        return descFlexfieldNumSeg2;
    }
    public void setDescFlexfieldNumSeg2(String descFlexfieldNumSeg2) {
        this.descFlexfieldNumSeg2 = descFlexfieldNumSeg2;
    }
    public String getDescFlexfieldNumSeg3() {
        return descFlexfieldNumSeg3;
    }
    public void setDescFlexfieldNumSeg3(String descFlexfieldNumSeg3) {
        this.descFlexfieldNumSeg3 = descFlexfieldNumSeg3;
    }
    public String getDescFlexfieldNumSeg4() {
        return descFlexfieldNumSeg4;
    }
    public void setDescFlexfieldNumSeg4(String descFlexfieldNumSeg4) {
        this.descFlexfieldNumSeg4 = descFlexfieldNumSeg4;
    }
    public String getDescFlexfieldNumSeg5() {
        return descFlexfieldNumSeg5;
    }
    public void setDescFlexfieldNumSeg5(String descFlexfieldNumSeg5) {
        this.descFlexfieldNumSeg5 = descFlexfieldNumSeg5;
    }
    public String getDescFlexfieldNumSeg6() {
        return descFlexfieldNumSeg6;
    }
    public void setDescFlexfieldNumSeg6(String descFlexfieldNumSeg6) {
        this.descFlexfieldNumSeg6 = descFlexfieldNumSeg6;
    }
    public String getDescFlexfieldNumSeg7() {
        return descFlexfieldNumSeg7;
    }
    public void setDescFlexfieldNumSeg7(String descFlexfieldNumSeg7) {
        this.descFlexfieldNumSeg7 = descFlexfieldNumSeg7;
    }
    public String getDescFlexfieldNumSeg8() {
        return descFlexfieldNumSeg8;
    }
    public void setDescFlexfieldNumSeg8(String descFlexfieldNumSeg8) {
        this.descFlexfieldNumSeg8 = descFlexfieldNumSeg8;
    }
    public String getDescFlexfieldNumSeg9() {
        return descFlexfieldNumSeg9;
    }
    public void setDescFlexfieldNumSeg9(String descFlexfieldNumSeg9) {
        this.descFlexfieldNumSeg9 = descFlexfieldNumSeg9;
    }
    public String getDescFlexfieldNumSeg10() {
        return descFlexfieldNumSeg10;
    }
    public void setDescFlexfieldNumSeg10(String descFlexfieldNumSeg10) {
        this.descFlexfieldNumSeg10 = descFlexfieldNumSeg10;
    }
    public String getDescFlexfieldNumSeg11() {
        return descFlexfieldNumSeg11;
    }
    public void setDescFlexfieldNumSeg11(String descFlexfieldNumSeg11) {
        this.descFlexfieldNumSeg11 = descFlexfieldNumSeg11;
    }
    public String getDescFlexfieldNumSeg12() {
        return descFlexfieldNumSeg12;
    }
    public void setDescFlexfieldNumSeg12(String descFlexfieldNumSeg12) {
        this.descFlexfieldNumSeg12 = descFlexfieldNumSeg12;
    }
    public String getDescFlexfieldDtSeg1() {
        return descFlexfieldDtSeg1;
    }
    public void setDescFlexfieldDtSeg1(String descFlexfieldDtSeg1) {
        this.descFlexfieldDtSeg1 = descFlexfieldDtSeg1;
    }
    public String getDescFlexfieldDtSeg2() {
        return descFlexfieldDtSeg2;
    }
    public void setDescFlexfieldDtSeg2(String descFlexfieldDtSeg2) {
        this.descFlexfieldDtSeg2 = descFlexfieldDtSeg2;
    }
    public String getDescFlexfieldDtSeg3() {
        return descFlexfieldDtSeg3;
    }
    public void setDescFlexfieldDtSeg3(String descFlexfieldDtSeg3) {
        this.descFlexfieldDtSeg3 = descFlexfieldDtSeg3;
    }
    public String getDescFlexfieldDtSeg4() {
        return descFlexfieldDtSeg4;
    }
    public void setDescFlexfieldDtSeg4(String descFlexfieldDtSeg4) {
        this.descFlexfieldDtSeg4 = descFlexfieldDtSeg4;
    }
    public String getDescFlexfieldDtSeg5() {
        return descFlexfieldDtSeg5;
    }
    public void setDescFlexfieldDtSeg5(String descFlexfieldDtSeg5) {
        this.descFlexfieldDtSeg5 = descFlexfieldDtSeg5;
    }
    public String getDescFlexfieldDtSeg6() {
        return descFlexfieldDtSeg6;
    }
    public void setDescFlexfieldDtSeg6(String descFlexfieldDtSeg6) {
        this.descFlexfieldDtSeg6 = descFlexfieldDtSeg6;
    }
    public String getDescFlexfieldDtSeg7() {
        return descFlexfieldDtSeg7;
    }
    public void setDescFlexfieldDtSeg7(String descFlexfieldDtSeg7) {
        this.descFlexfieldDtSeg7 = descFlexfieldDtSeg7;
    }
    public String getDescFlexfieldDtSeg8() {
        return descFlexfieldDtSeg8;
    }
    public void setDescFlexfieldDtSeg8(String descFlexfieldDtSeg8) {
        this.descFlexfieldDtSeg8 = descFlexfieldDtSeg8;
    }
    public String getDescFlexfieldDtSeg9() {
        return descFlexfieldDtSeg9;
    }
    public void setDescFlexfieldDtSeg9(String descFlexfieldDtSeg9) {
        this.descFlexfieldDtSeg9 = descFlexfieldDtSeg9;
    }
    public String getDescFlexfieldDtSeg10() {
        return descFlexfieldDtSeg10;
    }
    public void setDescFlexfieldDtSeg10(String descFlexfieldDtSeg10) {
        this.descFlexfieldDtSeg10 = descFlexfieldDtSeg10;
    }
    public String getDescFlexfieldDtSeg11() {
        return descFlexfieldDtSeg11;
    }
    public void setDescFlexfieldDtSeg11(String descFlexfieldDtSeg11) {
        this.descFlexfieldDtSeg11 = descFlexfieldDtSeg11;
    }
    public String getDescFlexfieldDtSeg12() {
        return descFlexfieldDtSeg12;
    }
    public void setDescFlexfieldDtSeg12(String descFlexfieldDtSeg12) {
        this.descFlexfieldDtSeg12 = descFlexfieldDtSeg12;
    }
    public String getCustomerTypeInput() {
        return customerTypeInput;
    }
    public void setCustomerTypeInput(String customerTypeInput) {
        this.customerTypeInput = customerTypeInput;
    }
    public String getCustomerStatusInput() {
        return customerStatusInput;
    }
    public void setCustomerStatusInput(String customerStatusInput) {
        this.customerStatusInput = customerStatusInput;
    }
    public String getGnrstatus() {
        return gnrstatus;
    }
    public void setGnrstatus(String gnrstatus) {
        this.gnrstatus = gnrstatus;
    }
    public String getErrorMsg() {
        return errorMsg;
    }
    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
    public String getEnd() {
        return end;
    }
    public void setEnd(String end) {
        this.end = end;
    }
    public String getMappedEmailAddr() {
        return mappedEmailAddr;
    }
    public void setMappedEmailAddr(String mappedEmailAddr) {
        this.mappedEmailAddr = mappedEmailAddr;
    }
    public String getSrcSysId() {
        return srcSysId;
    }
    public void setSrcSysId(String srcSysId) {
        this.srcSysId = srcSysId;
    }
    public String getPartySiteName() {
        return partySiteName;
    }
    public void setPartySiteName(String partySiteName) {
        this.partySiteName = partySiteName;
    }
    public String getContactPersonName1() {
        return contactPersonName1;
    }
    public void setContactPersonName1(String contactPersonName1) {
        this.contactPersonName1 = contactPersonName1;
    }
}
