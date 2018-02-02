package crypto.spy.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jaunt.JauntException;
import com.jaunt.UserAgent;

public class APIConnection {
	
	@SuppressWarnings("rawtypes")
	protected Object mapResponse(String response, TypeReference type) {
		try {
			return new ObjectMapper().readValue(response, type);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} 
	}
	
  protected static String GET(String url) {
	  try{
			UserAgent userAgent = new UserAgent();
			userAgent.sendGET(url);//send request
			return userAgent.json.toString();
		}
		catch(JauntException e){
			System.err.println(e);
			return "";
		}
  }

	
	
	 /**
     * Implements HTTP's GET method
     *
     * @param requestAddress Address to be loaded
     * @return Response if successful, else <code>null</code>
     * @see <a href="http://www.w3.org/Protocols/rfc2616/rfc2616-sec9.html">HTTP - (9.3) GET</a>
     */
//    protected String httpGET(String requestAddress) {
//        URL request;
//        HttpURLConnection connection = null;
//        BufferedReader reader = null;
//
//        String s;
//        String response = null;
//
//        try {
//            request = new URL(requestAddress);
//            connection = (HttpURLConnection) request.openConnection();
//
//            connection.setRequestMethod("GET");
//            connection.setUseCaches(false);
//            connection.setDoInput(true);
//            connection.setDoOutput(false);
//            connection.setRequestProperty("Accept-Encoding", "gzip, deflate");
//            connection.setRequestProperty("Accept", "application/json");
//            connection.connect();
//
//            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
//                String encoding = connection.getContentEncoding();
//
//                try {
//                    if (encoding != null && encoding.equalsIgnoreCase("gzip")) {
//                        reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(connection.getInputStream())));
//                    } else if (encoding != null && encoding.equalsIgnoreCase("deflate")) {
//                        reader = new BufferedReader(new InputStreamReader(new InflaterInputStream(connection.getInputStream(), new Inflater(true))));
//                    } else {
//                        reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
//                    }
//
//                    while ((s = reader.readLine()) != null) {
//                        response = s;
//                    }
//                } catch (IOException e) {
//                    System.err.println("Error: " + e.getMessage());
//                } finally {
//                    if (reader != null) {
//                        try {
//                            reader.close();
//                        } catch (IOException e) {
//                            System.err.println("Error: " + e.getMessage());
//                        }
//                    }
//                }
//            } else { // if HttpURLConnection is not okay
//                try {
//                    reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
//                    while ((s = reader.readLine()) != null) {
//                        response = s;
//                    }
//                } catch (IOException e) {
//                    System.err.println("Error: " + e.getMessage());
//                } finally {
//                    if (reader != null) {
//                        try {
//                            reader.close();
//                        } catch (IOException e) {
//                            System.err.println("Error: " + e.getMessage());
//                        }
//                    }
//                }
//
//                // if response is bad
//                System.err.println("Bad Response: " + response + "\n");
//                return null;
//            }
//        } catch (IOException e) {
//            System.err.println("Error: " + e.getMessage());
//            response = null;
//        } finally {
//            if (connection != null) {
//                connection.disconnect();
//            }
//        }
//
//        return response;
//    }

}
