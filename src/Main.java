import java.io.*;
import java.net.*;
import java.util.Scanner;

import org.json.*;

public class Main {
  private static final String api_key = "YXES52VS8NZ162RT";
  private static final String api_url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=IBM&interval=5min&apikey=demo";

  public static String getdata(String symbol){
	  String final_url = "https://www.alphavantage.co/query?function=TIME_SERIES_INTRADAY&symbol=" +
			  symbol + "&interval=5min&apikey="+ api_key;

	  StringBuilder result = new StringBuilder();
	  try{
		  URL url = new URL(final_url);
		  HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		  connection.setRequestMethod("GET");

		  BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		  String content;

		  while((content = in.readLine()) != null){
			  result.append(content);
		  }
		  in.close();
		  connection.disconnect();
	  }
	  catch (Exception e){
		  System.out.println("error happens");
		  e.printStackTrace();
	  }
	  return result.toString();
  }

  public static String parsedata(String response){
	  JSONObject jsonn = new JSONObject(response);
	  JSONObject timeseries = jsonn.getJSONObject("Time Series (5min)");
	  String latestTime = timeseries.keys().next();
	  JSONObject latestdata = timeseries.getJSONObject(latestTime);

	  double openPrice = latestdata.getDouble("1. open");
	  double highPrice = latestdata.getDouble("2. high");
	  double lowPrice = latestdata.getDouble("3. low");
	  double closePrice = latestdata.getDouble("4. close");
	  double volume = latestdata.getDouble("5. volume");

	  System.out.println("latest data time" + latestTime);
	  System.out.println("open price: " + openPrice);
	  System.out.println("high price: " + highPrice);
	  System.out.println("low price: " + lowPrice);
	  System.out.println("close price: " + closePrice);
	  System.out.println("volume: " + volume);

	  StringBuilder result = new StringBuilder();

	  return result.append("\n")
			  .append("   Time: " + latestTime).append("\n").append("\n")
			  .append("   Open price: "+openPrice).append("\n").append("\n")
			  .append("   High price: "+highPrice).append("\n").append("\n")
			  .append("   Low price: "+lowPrice).append("\n").append("\n")
			  .append("   Close price: "+closePrice).append("\n").append("\n")
			  .append("   Volume: "+volume).toString();
  }

  public static void main(String[] args){

	  while(true) {
		  System.out.println("enter symbol eg: Apple: 'AAPL' , Tesla: 'TSLA' , Netflix: 'NFLX' ");
		  Scanner abc = new Scanner(System.in);
		  String symbol = abc.next();

			  String response = getdata(symbol);
			  System.out.println("symbol: " + symbol);
			  parsedata(response);

	  }
  }
}
