package com.countries.controller;

import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.countries.beans.Country;
import com.teamdev.jxbrowser.browser.Browser;
import com.teamdev.jxbrowser.view.swing.BrowserView;

import com.teamdev.jxbrowser.engine.Engine;
import com.teamdev.jxbrowser.engine.EngineOptions;
import java.awt.BorderLayout;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;

@Controller
public class CountriesController {
	
	List<Country> countries = new ArrayList<Country>();
	
	@RequestMapping(method = RequestMethod.GET, value="/countries")
	  @ResponseBody
	  public void getSelectedRegion(@RequestParam(required=true) String countryId) {
		
		 try {
			 	
				URL url = new URL("http://api.worldbank.org/v2/country/"+countryId);
				HttpURLConnection conn = (HttpURLConnection) url.openConnection(); 
				conn.setRequestMethod("GET");
				conn.setRequestProperty("Accept", "application/json");

				if (conn.getResponseCode() != 200) {
					throw new RuntimeException("Failed : HTTP error code : "
							+ conn.getResponseCode());
				}				

				BufferedReader br = new BufferedReader(new InputStreamReader(
					(conn.getInputStream())));

				String output;
				System.out.println("Output from Server .... \n");
				Country country = new Country();
				while ((output = br.readLine()) != null) {

					if(output.contains("region id") && !output.contains("region id") ) {
						String region_id = output.substring(output.indexOf(">") + 1, output.lastIndexOf("<"));	
						country.setRegionId(region_id);
							
					}
					
					if(output.contains("capitalCity")) {
						String capitalCity = output.substring(output.indexOf(">") + 1, output.lastIndexOf("<"));	
						country.setCapital(capitalCity);
							
					}
					
					if(output.contains("name")) {
						String name = output.substring(output.indexOf(">") + 1, output.lastIndexOf("<"));	
						country.setName(name);
							
					}
					if(output.contains("incomeLevel")) {
						String incomeLevel = output.substring(output.indexOf(">") + 1, output.lastIndexOf("<"));	
						country.setIncomeLevel(incomeLevel);							
					}
					
					if(output.contains("lendingType")) {
						String lendingType = output.substring(output.indexOf(">") + 1, output.lastIndexOf("<"));	
						country.setLendingType(lendingType);						
					}
				 
				 System.out.println(output);

				}
				countries.add(country);
				System.out.println("country is "+country);

				conn.disconnect();

			  } catch (MalformedURLException e) {

				e.printStackTrace();

			  } catch (IOException e) {

				e.printStackTrace();

			  }	
	  }
	

	@RequestMapping(method = RequestMethod.GET, value="/display")
	  @ResponseBody 
	  public void viewCapitalInMap(){
		  final Browser browser = new Browser();
	       BrowserView browserView = new BrowserView(browser);

	       JFrame frame = new JFrame(“map.html”);
	       frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	       frame.add(browserView, BorderLayout.CENTER);
	       frame.setSize(900, 500);
	       frame.setLocationRelativeTo(null);
	       frame.setVisible(true);

	       ((Object) browser).loadURL("C://map.html");
	   }

}
