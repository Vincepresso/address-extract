package com.vinhuang.processors;

// Import appropriate packages

public class AddressExtractProcessor implements ItemProcessor<Agent, AddressExtractMOutput> {

	private static final Logger LOGGER = LoggerFactory.getLogger(AddressExtractProcessor.class);
	private static final COUNTRY_USA = "USA";
	private Date cycleDate;

	/**
	 * Before step will run prior to processing. Logging can be added here.
	 *
	 * @param StepExecution stepExecution
	 * @return
	*/
	@Override
	@BeforeStep
	public void beforeStep(StepExecution stepExecution) {
		super.beforeStep(stepExecution);
	}

	/**
	 * This method will extract address from agent database. It retrieves agent data from SQL
	 * I omitted complicated logic that was previously included, and replaced it with a simple
	 * IF statement that checks the country of origin
	 *
	 * @param AgentKey agentKey
	 * @return AddressExtractOutput output
	*/
	@Override
	public AddressExtractOutput process(Agent agent) throws Exception {
		// defaultString will set the value to "" if it's null
		AddressExtract output = new AddressExtract();
		if(cycleDate != null && COUNTRY_USA.equalsIgnoreCase(agent.getCountry()) {
			output.setAddressLine1(defaultString(agent.getAddressLine1()));
			output.setAddressLine2(defaultString(agent.getAddressLine2()));
			output.setAddressLine3(defaultString(agent.getAddressLine3()));
			output.setCity(defaultString(agent.getCity()));
			output.setZip(defaultString(agent.getZip()));
		} else {
			output.setAddressLine1(defaultString(agent.getAddressLine1()));
			output.setAddressLine2(defaultString(agent.getAddressLine2()));
			output.setAddressLine3(defaultString(agent.getAddressLine3()));
			output.setCity("");
			output.setZip("");
		}
		return output;
	}

	/**
	 * This Data Access Object is used to run a SELECT query against the database
	 * by using AgentKey as its input (translated to the WHERE clause in SQL)
	 *
	 * @param AgentKey agentKey
	 * @return AddressExtractOutput output
	*/
	public void setAgentDataDao(AgentDataDao agentDataDao) {
		this.agentTopicDao = agentTopicDao;
	}
	
	public void setCycleDate(Date cycleDate) {
		this.cycleDate = cycleDate;
	}

}
