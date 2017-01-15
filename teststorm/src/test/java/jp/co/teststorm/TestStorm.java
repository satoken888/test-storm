package jp.co.teststorm;

import storm.contrib.spring.topology.LocalTopologySubmitter;

public class TestStorm {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String[] array = {"spring-context.xml", "exclamationTopologySubmission","60000"};
		LocalTopologySubmitter.main(array);
	}

}
