# TATOC

 String s="";
		 File file = new File("C:\\Users\\ankitmalik\\append.txt");
		 FileWriter fr = new FileWriter(file, true);
		 BufferedWriter br = new BufferedWriter(fr);
		 PrintWriter pr = new PrintWriter(br);
     try
    {
        ChromeOptions options = new ChromeOptions();
        // add whatever extensions you need
        // for example I needed one of adding proxy, and one for blocking
        // images
        // options.addExtensions(new File(file, "proxy.zip"));
        // options.addExtensions(new File("extensions",
        // "Block-image_v1.1.crx"));

        DesiredCapabilities cap = DesiredCapabilities.chrome();
        cap.setCapability(ChromeOptions.CAPABILITY, options);

        // set performance logger
        // this sends Network.enable to chromedriver
        LoggingPreferences logPrefs = new LoggingPreferences();
        logPrefs.enable(LogType.PERFORMANCE, Level.ALL);
        cap.setCapability(CapabilityType.LOGGING_PREFS, logPrefs);

        driver = new ChromeDriver(cap);
        String url="https://www.javatpoint.com";
        // navigate to the page
        System.out.println("Navigate to " + url);
        driver.navigate().to(url);

        // and capture the last recorded url (it may be a redirect, or the
        // original url)
        String currentURL = driver.getCurrentUrl();

        // then ask for all the performance logs from this request
        // one of them will contain the Network.responseReceived method
        // and we shall find the "last recorded url" response
        LogEntries logs = driver.manage().logs().get("performance");

        int status = -1;
       

        pr.println("\nList of log entries:\n");

        for (Iterator<LogEntry> it = logs.iterator(); it.hasNext();)
        {
            LogEntry entry = it.next();

            try
            {
                JSONObject json = new JSONObject(entry.getMessage());

                pr.println(json.toString());

                JSONObject message = json.getJSONObject("message");
                String method = message.getString("method");

                if (method != null
                        && "Network.responseReceived".equals(method))
                {
                    JSONObject params = message.getJSONObject("params");

                    JSONObject response = params.getJSONObject("response");
                    String messageUrl = response.getString("url");

                    if (currentURL.equals(messageUrl))
                    {
                        status = response.getInt("status");

                        pr.println(
                                "---------- bingo !!!!!!!!!!!!!! returned response for "
                                        + messageUrl + ": " + status);

                        pr.println(
                                "---------- bingo !!!!!!!!!!!!!! headers: "
                                        + response.get("headers"));
                        s=s+response.get("headers");
                    }
                }
            } catch (JSONException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        System.out.println("\nstatus code: " + status);
    } 
    
    finally
    {
        if (driver != null)
        {
           // driver.quit();
        	System.out.println("malik"+s);
        }
    }
pr.close();
br.close();
fr.close();
