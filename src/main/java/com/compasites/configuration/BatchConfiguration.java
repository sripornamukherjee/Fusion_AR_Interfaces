package com.compasites.configuration;

import com.compasites.constants.Constants;
import com.compasites.helper.IntegrationServiceHelper;
import com.compasites.helper.ReportRunHelper;
import com.compasites.listener.*;
import com.compasites.pojo.Customer;
import com.compasites.processor.CustomerProcessor;
import com.compasites.utils.FileUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.*;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.support.CompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.io.FileSystemResource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sobhan Babu on 19-04-2016.
 */
@Configuration
@EnableBatchProcessing
//@EnableScheduling
public class BatchConfiguration {

    static Logger LOG = LoggerFactory.getLogger(BatchConfiguration.class);

    @Value("${customer.inputDir}")
    private String inputDir;

    @Value("${customer.filelookup.dir}")
    private String lookupDir;

    @Value("${customer.inputfile}")
    private String customerFile;

    @Value("${customer.output.folder}")
    private String outputFolder;

    @Value("${customer.output.partysitesfile}")
    private String partysitesFile;

    @Value("${customer.output.partysiteusesfile}")
    private String prtysiteusesFile;

    @Value("${customer.output.accountfile}")
    private String accountFile;

    @Value("${customer.output.accountsitesfile}")
    private String accountsitesFile;

    @Value("${customer.output.accountsiteusesfile}")
    private String accntSiteUsesFile;

    @Value("${customer.output.locationsfile}")
    private String locationFile;

    @Value("${customer.output.accountcontactsfile}")
    private String accntContctsFile;

    @Value("${customer.output.accntcontacts}")
    private String accntCntctsFile;

    @Value("${customer.output.contactroles}")
    private String contctRolesFile;

    @Value("${customer.output.partyrelationship}")
    private String prtyRelFile;

    @Value("${customer.output.profilesintall}")
    private String profileIntallFile;

    @Value("${customer.output.contacts}")
    private String cntctsFile;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    public JobRepository jobRepository;

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;

    @Bean
    public FlatFileItemReader<Customer> reader() {
        File dirPath = new File(lookupDir);
        String inputFile = FileUtil.lastModifiedFile(dirPath, Constants.EXTENSION_FILES);
        if(inputFile == null){
            inputFile = Constants.CUSTOMER_FILENAME;
        	//throw new RuntimeException("Input file missing in input directory...");
        }

        Customer.customerFile = inputFile;
        FlatFileItemReader<Customer> reader = new FlatFileItemReader<Customer>();
        try {
        	//reader.setResource(new FileSystemResource(inputDir + customerFile));
        	reader.setResource(new FileSystemResource(inputDir + inputFile));
        	reader.setLinesToSkip(1);
        	reader.setStrict(false);
        	reader.setLineMapper(new DefaultLineMapper<Customer>() {{
	            setLineTokenizer(new DelimitedLineTokenizer() {{
	                setNames(new String[] { "srcSysId", "customerTypeInput","customerId",
	                        "customerName", "contactPersonName", "emailAddress", "contactPhoneNumber",
	                        "billingAddr1", "billingAddr2", //"billingAddr3", //"billingAddr4",
	                        "city", "inputState", "country", "postalCode",
	                        "effectiveFrom", "customerStatusInput" });
	            }});
	            setFieldSetMapper(new BeanWrapperFieldSetMapper<Customer>() {{
	                setTargetType(Customer.class);
	            }});
	        }});
        }
        catch(Exception e) {
        	throw new RuntimeException("An error occurred while reading the input file. Please check the input file format.");
        }

        return reader;
    }

    @Bean
    public CustomerProcessor processor() {
        return new CustomerProcessor();
    }

    @Bean
    public PartyCustomWriter customPartyWriter() {
        return new PartyCustomWriter();
    }

    @Bean
    public ContactPointWriter contactPointWriter() {
        return new ContactPointWriter();
    }

    @Bean
    public CustomerAccountContactResponsibilities customerAccntCotctResWriter() {
        return new CustomerAccountContactResponsibilities();
    }

    @Bean
    public CompositeItemWriter<Customer> writer() {
        List<ItemWriter> list = new ArrayList<ItemWriter>();
        CompositeItemWriter compositeItemWriter = new CompositeItemWriter();
        compositeItemWriter.setDelegates(list);

        //party
        list.add(customPartyWriter());

        //party sites
        list.add(getItemWriter(outputFolder + partysitesFile,
                new String[] {"batchIdentifier","prtyOrigSystem","customerId",
                        "prtySiteOrgSystem","customerId","locOriginalSystem",
                        "customerId","insertUpdateIndicator","customerId", "customerId",
                        //"customerId","insertUpdateIndicator","partySiteName", "emptyVal",
                        "prtySiteFromDt","prtySiteToDt",
                        "mailStop","identifyingAddr","siteLanguage",
                        "userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30",
                        "relationSrcSys","relationSrcSysRef","end"}));

        //party site uses
        list.add(getItemWriter(outputFolder + prtysiteusesFile,
                new String[] {"batchIdentifier","prtyOrigSystem","customerId",
                        "prtySiteOrgSystem","customerId","prtSiteUseType",
                        "primaryIndicator","insertUpdateIndicator", "fromDt",
                        "toDt", "userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30",
                        "prtySiteUseOrgSys", "customerId","end"}));

        //accounts
        list.add(getItemWriter(outputFolder + accountFile,
                new String[] {"batchIdentifier","custAccntSrcSys","customerId",
                        "prtyOrigSystem","customerId","customerId",
                        "insertUpdateIndicator","accntType", "custClass",
                        "accntDesc", "accntEstDt", "userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30", "accntTerminationDt",
                        "end"}));

        //account sites
        list.add(getItemWriter(outputFolder + accountsitesFile,
                new String[] {"batchIdentifier","custAccntSrcSys","customerId",
                        "accntSiteSrcSys","customerId","prtySiteOrgSystem",
                        "customerId","siteLanguage", "insertUpdateIndicator",
                        "custCategoryCd", "transalatedCustNm", "accntAddrSet",
                        "fromDt", "toDt","keyAccnt","userDefcontxtPrompt","descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30","end"}));

        //account site uses
        list.add(getItemWriter(outputFolder + accntSiteUsesFile,
                new String[] {"batchIdentifier","accntSiteSrcSys","customerId",
                        "accntSitePurposeSrcSys","customerId","purpose",
                        "primaryIndicator","insertUpdateIndicator", "site",
                        "accntAddPurposeSet", "purposeFromDt", "purposeToDt",
                        "userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30","end"}));



        //account contacts
        list.add(getItemWriter(outputFolder + accntContctsFile,
                new String[] {"batchIdentifier","custAccntSrcSys","customerId",
                        "accntSiteSrcSys","customerId","accntSiteSrcSys","accntSiteSrcSysRef",
                        "roleType","accntCntctsPrimaryIndicator","insertUpdateIndicator","emptyVal",
                        "userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30","descFlexfieldNumSeg1",
                        "descFlexfieldNumSeg2", "descFlexfieldNumSeg3",
                        "descFlexfieldNumSeg4", "descFlexfieldNumSeg5", "descFlexfieldNumSeg6",
                        "descFlexfieldNumSeg7", "descFlexfieldNumSeg8", "descFlexfieldNumSeg9",
                        "descFlexfieldNumSeg10", "descFlexfieldNumSeg11", "descFlexfieldNumSeg12",
                        "descFlexfieldDtSeg1", "descFlexfieldDtSeg2", "descFlexfieldDtSeg3",
                        "descFlexfieldDtSeg4", "descFlexfieldDtSeg5", "descFlexfieldDtSeg6",
                        "descFlexfieldDtSeg7", "descFlexfieldDtSeg8", "descFlexfieldDtSeg9",
                        "descFlexfieldDtSeg10", "descFlexfieldDtSeg11", "descFlexfieldDtSeg12",
                        "accntCntctsRelSrc","relSrcSystemRef",
                        "end"}));

        //contact points
        list.add(contactPointWriter());

        //contact roles
        list.add(getItemWriter(outputFolder + contctRolesFile,
                new String[] {"batchIdentifier","emptyVal","emptyVal","insertUpdateIndicator","contctRoleOrigSys",
                        "contctRoleOrigSysRef","cntctRelationSrcSys","relSrcSystemRef",
                        "roleType","contctRoleLevel","contctPrimaryRole","contctRoleTypePrimary",
                        /*"userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30","descFlexfieldNumSeg1",
                        "descFlexfieldNumSeg2", "descFlexfieldNumSeg3",
                        "descFlexfieldNumSeg4", "descFlexfieldNumSeg5", "descFlexfieldNumSeg6",
                        "descFlexfieldNumSeg7", "descFlexfieldNumSeg8", "descFlexfieldNumSeg9",
                        "descFlexfieldNumSeg10", "descFlexfieldNumSeg11", "descFlexfieldNumSeg12",
                        "descFlexfieldDtSeg1", "descFlexfieldDtSeg2", "descFlexfieldDtSeg3",
                        "descFlexfieldDtSeg4", "descFlexfieldDtSeg5", "descFlexfieldDtSeg6",
                        "descFlexfieldDtSeg7", "descFlexfieldDtSeg8", "descFlexfieldDtSeg9",
                        "descFlexfieldDtSeg10", "descFlexfieldDtSeg11", "descFlexfieldDtSeg12",*/
                        "end"}));

        //contacts
        list.add(getItemWriter(outputFolder + cntctsFile,
                new String[] {"batchIdentifier","insertUpdateIndicator","contactNumber",
                        "deptCode","deptName",
                        "jobTitle","jobTitleCode",
                        "userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30",
                        "descFlexfieldNumSeg1", "descFlexfieldNumSeg2", "descFlexfieldNumSeg3",
                        "descFlexfieldNumSeg4", "descFlexfieldNumSeg5", "descFlexfieldNumSeg6",
                        "descFlexfieldNumSeg7", "descFlexfieldNumSeg8", "descFlexfieldNumSeg9",
                        "descFlexfieldNumSeg10", "descFlexfieldNumSeg11", "descFlexfieldNumSeg12",
                        "descFlexfieldDtSeg1", "descFlexfieldDtSeg2", "descFlexfieldDtSeg3",
                        "descFlexfieldDtSeg4", "descFlexfieldDtSeg5", "descFlexfieldDtSeg6",
                        "descFlexfieldDtSeg7", "descFlexfieldDtSeg8", "descFlexfieldDtSeg9",
                        "descFlexfieldDtSeg10", "descFlexfieldDtSeg11", "descFlexfieldDtSeg12",
                        "relSrcSystem", "relSrcSystemRef", "prtySiteOrgSystem",
                        "customerId", "end"}));

        //Party relationshiip
        list.add(getItemWriter(outputFolder + prtyRelFile,
                new String[] {"batchIdentifier","subRelPrtyOrigSystem",
                        "subRelPrtyOrigSystemRef","objRelPrtyOrigSystem","customerId","relType","relCode",
                        "fromDt","insertUpdateIndicator","toDt",
                        "userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30",
                        "descFlexfieldNumSeg1", "descFlexfieldNumSeg2", "descFlexfieldNumSeg3",
                        "descFlexfieldNumSeg4", "descFlexfieldNumSeg5", "descFlexfieldNumSeg6",
                        "descFlexfieldNumSeg7", "descFlexfieldNumSeg8", "descFlexfieldNumSeg9",
                        "descFlexfieldNumSeg10", "descFlexfieldNumSeg11", "descFlexfieldNumSeg12",
                        "descFlexfieldDtSeg1", "descFlexfieldDtSeg2", "descFlexfieldDtSeg3",
                        "descFlexfieldDtSeg4", "descFlexfieldDtSeg5", "descFlexfieldDtSeg6",
                        "descFlexfieldDtSeg7", "descFlexfieldDtSeg8", "descFlexfieldDtSeg9",
                        "descFlexfieldDtSeg10", "descFlexfieldDtSeg11", "descFlexfieldDtSeg12",
                        "subPrtyType","objPrtyType","subRelPrtyOrigSystem","relSrcSystemRef",
                        "end"}));

        //customer account contact responsibilities
        list.add(customerAccntCotctResWriter());

        //Customer account and or site profiles
        list.add(getItemWriter(outputFolder + profileIntallFile,
                new String[] {"batchIdentifier","insertUpdateIndicator","custAccntSrcSystem",
                        "customerId","accntSiteSrcSystem","accntSiteSrcSystemRef","custProfileClass",
                        "collectorName","sendCreditBal","includeInCreditCheck","creditHold",
                        "allowDiscount","sendDunningLetters","enableLateCharges","sendStatment",
                        "tolerance","taxPrintingOption","accntStatus","autoCashRuleSet",
                        "creditRating","discountGraceDays","interestDaysPeriod","overrideTerms",
                        "receiptGraceDays","collectible","riskCode","paymentTerms",
                        "statmentCycle","interestCalcFormula","groupingRule","currency",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "prtySrcSystem","customerId","emptyVal","emptyVal","orgId",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "emptyVal","emptyVal","emptyVal","emptyVal","emptyVal",
                        "end"}));

        //locations
        list.add(getItemWriter(outputFolder + locationFile,
                new String[] {"batchIdentifier","locOriginalSystem","customerId",
                        "insertUpdateIndicator","county1","billingAddr1",
                        //"billingAddr2","billingAddr3", "billingAddr4", "city", "inputState", "province",
                        "billingAddr2","billingAddr3", "emptyVal", "city", "inputState", "province",
                        "county", "postalCode", "postalCdExtn","locLang",
                        "description", "shortDesc", "salesTaxGeocode", "salesTaxInCityLmts",
                        "timezoneCdIdent", "addLine1", "addValidSrc",
                        "retAddrValidCode", "addValidDt", "addEffectiveDt",
                        "addExpireDt","validIndicator","validIneligbleIndicator",
                        "interfaceStatus","errorIdentifier",
                        "userDefcontxtPrompt", "descrFlexfieldSeg1",
                        "descrFlexfieldSeg2", "descrFlexfieldSeg3", "descrFlexfieldSeg4",
                        "descrFlexfieldSeg5", "descrFlexfieldSeg6", "descrFlexfieldSeg7",
                        "descrFlexfieldSeg8", "descrFlexfieldSeg9", "descrFlexfieldSeg10",
                        "descrFlexfieldSeg11", "descrFlexfieldSeg12", "descrFlexfieldSeg13",
                        "descrFlexfieldSeg14", "descrFlexfieldSeg15", "descrFlexfieldSeg16",
                        "descrFlexfieldSeg17", "descrFlexfieldSeg18", "descrFlexfieldSeg19",
                        "descrFlexfieldSeg20", "descrFlexfieldSeg21", "descrFlexfieldSeg22",
                        "descrFlexfieldSeg23", "descrFlexfieldSeg24", "descrFlexfieldSeg25",
                        "descrFlexfieldSeg26", "descrFlexfieldSeg27", "descrFlexfieldSeg28",
                        "descrFlexfieldSeg29", "descrFlexfieldSeg30",
                        "descFlexfieldNumSeg1", "descFlexfieldNumSeg2", "descFlexfieldNumSeg3",
                        "descFlexfieldNumSeg4", "descFlexfieldNumSeg5", "descFlexfieldNumSeg6",
                        "descFlexfieldNumSeg7", "descFlexfieldNumSeg8", "descFlexfieldNumSeg9",
                        "descFlexfieldNumSeg10", "descFlexfieldNumSeg11", "descFlexfieldNumSeg12",
                        "descFlexfieldDtSeg1", "descFlexfieldDtSeg2", "descFlexfieldDtSeg3",
                        "descFlexfieldDtSeg4", "descFlexfieldDtSeg5", "descFlexfieldDtSeg6",
                        "descFlexfieldDtSeg7", "descFlexfieldDtSeg8", "descFlexfieldDtSeg9",
                        "descFlexfieldDtSeg10", "descFlexfieldDtSeg11", "descFlexfieldDtSeg12",
                        "gnrstatus","end"}));

        return compositeItemWriter;
    }

    private FlatFileItemWriter<Customer> getItemWriter(String fileName, String properties[]){
        FlatFileItemWriter<Customer> writer = new FlatFileItemWriter<Customer>();
        writer.setResource(new FileSystemResource(fileName));
        DelimitedLineAggregator<Customer> delLineAgg = new DelimitedLineAggregator<Customer>();
        delLineAgg.setDelimiter(",");
        BeanWrapperFieldExtractor<Customer> fieldExtractor = new BeanWrapperFieldExtractor<Customer>();

        fieldExtractor.setNames(properties);
        delLineAgg.setFieldExtractor(fieldExtractor);
        writer.setLineAggregator(delLineAgg);

        return writer;
    }

    @Bean
    public JobExecutionListener listener() {
        return new CustomerJobCompletionListener();
    }

    /*@Scheduled(cron = "${cron.expression.customer}")
    public void perform() throws Exception {
        LOG.info("Customer job started ---");

        JobParameters param = new JobParametersBuilder().addString("JobID",
                String.valueOf(System.currentTimeMillis())).toJobParameters();
        JobExecution execution = jobLauncher.run(importUserJob(), param);

        LOG.info("Customer job finished with status :" + execution.getStatus());
    }*/

    @Bean
    public Job importUserJob() {
        return jobBuilderFactory.get("importUserJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(step1())
                .end()
                .build();
    }

    @Bean
    public Step step1() {
        return stepBuilderFactory.get("step1")
                .<Customer, Customer> chunk(1)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                //.listener(itemWritelistener())  // add listener if needed
                .build();
    }

    @Bean
    @Scope("prototype")
    public IntegrationServiceHelper integrationServiceHelper() {
        return new IntegrationServiceHelper();
    }

    @Bean
    @Scope("prototype")
    public ReportRunHelper reportRunHelper() {
        return new ReportRunHelper();
    }
}
