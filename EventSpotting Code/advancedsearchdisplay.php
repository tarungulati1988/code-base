<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Event Spotting</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(document).ready(function(){
		$(document).pngFix();
	});
</script>
<script type="text/javascript">
  function makeEnable()
  {
      if(document.getElementById("HH").value != "default")
        {
          var x=document.getElementById("MM")
          //document.write(document.getElementById("HH").value)
          x.disabled=false
        }
      else
        {
          var x=document.getElementById("MM")
          //document.write(document.getElementById("HH").value)
          document.getElementById("MM").value = "default"
          x.disabled=true 
        }
  }

  function validateAdvSearchForm()
  {
      var timeHH = document.getElementById("HH").value
      var timeMM = document.getElementById("MM").value
      var city = document.getElementById("city").value
      var date = document.getElementById("date").value
      var zipcode = document.getElementById("zipcode").value
      var catBusiness = document.getElementById("Business").checked
      var catArt = document.getElementById("Art Exhibitions").checked
      var catCareer = document.getElementById("Career Fairs").checked
      var catCharity = document.getElementById("Charity").checked
      var catParty = document.getElementById("Parties").checked
      var catPlay = document.getElementById("Plays").checked
      var catReunion = document.getElementById("Reunions").checked
      var catSport = document.getElementById("Sports").checked
      var catTech = document.getElementById("Tech Talks").checked
      var catConcert = document.getElementById("Concerts").checked

      if(timeHH=="default" && timeMM=="default" && city=="default" && !zipcode && !date && catBusiness==false && catArt==false && catCareer==false && catCharity==false && catConcert==false && catParty==false && catPlay==false && catReunion==false && catSport==false && catTech==false)
        {
          alert("Please select some option!");
          return false;
        }
      else
        return true;
  }
</script>
<script type="text/javascript">
  function validateForm()
    {
        var submitSearch = document.getElementById("searchbar").value
        
        if(submitSearch == "default")
          {
            alert("Please select a category!");
            return false;
          }
        else
          return true;
    }
</script>
<script type="text/javascript" src="/classpath/js/mainnav2.js"></script>
<?php
  session_start();
?>
</head>
<body>
<div id="bodyWrap">
  <div class="wrapper">
    <div class="toplinks wid980"> <span class="dbfr padTB5">
      <?php
        include 'username.php';
      ?>
    </span> </div>
    <div class="headerarea">
      <!--Header Starts-->
      <div class="header">
        <div class="logo"><a href="/login.html"><img src="eventspottingIcon.png" alt="Event Spotting" border="0" height="84" width="143"/></a></div>
        <div class="topsearch dbfr">
          <div class="topsearch_topcurve dbfl"></div>
          <div class="topsearch_mid dbfl">
            <div class="searchico dbfl"></div>
             <div class="searcharea dbfl">
              <p class="bold">Search</p>
              
                        <form action="searchdisplay.php" method="post" onsubmit="return validateForm();">
                          <select class="search_category" name="searchbar" id="searchbar">
                            <option value="default">Categories</option>
                            <option value="Art Exhibitions">Art Exhibitions</option>
                            <option value="Business">Business</option>
                            <option value="Career Fairs">Career Fairs</option>
                            <option value="Charity">Charity</option>
                            <option value="Concerts">Concerts</option>
                            <option value="Parties">Parties</option>
                            <option value="Plays">Plays</option>
                            <option value="Reunions">Reunions</option>
                            <option value="Sports">Sports</option>
                            <option value="Tech Talks">Tech Talks</option>
                          </select>
                          <input type="submit" value="Search" name="search" class="searchbtn orange"/>
                        </form>
              <p class="bold"><a href="adv_search.php">Advanced Search </a></p>
            </div>
          </div>
          <div class="topsearch_btmcurve dbfl"></div>
        </div>
      </div>
    </div>
    <div class="mc_landingpage_area">
      <div class="maincontent">
        <div id="menu" class="dbfl">
          <div id="mainnavWrap" >
            <!-- Main Nav Wrapper -->
            <div class="leftbckg dbfl"></div>
            <div class="mid dbfl">
              <ul id="nav">
                <li class="menu1" ><a href="home.php" id="u1">Home</a></li>
                <li class="menu1"><a href="event.php" class="current1" id="u2"> Events</a></li>
                <li class="menu1"><a href="pastevent.php" id="u2"> Past Events</a></li>
                <?php
                  if (isset($_SESSION['email']) ) {
                      echo '<li class="menu1"><a href="create_event.php" id="u2">Create Events</a></li>';
                      echo '<li class="menu1"><a href="rsvpevents.php" id="u2">RSVP</a></li>';
                      echo '<li class="menu1"><a href="myevents.php" id="u2">My Events</a></li>';
                      echo '<li class="menu1"><a href="myinterests.php" id="u2">My Interests</a></li>';
                    }
                ?>
              </ul>
            </div>
            <div class="rightbckg dbfl"></div>
            <!-- End of Main Nav Wrapper -->
          </div>
          <div id="nav2Wrap">
            <!-- Nav 2 Wrapper -->
            <div class="leftbckg dbfl"></div>
            <div class="rightbckg dbfl"></div>
            <!-- End of Nav 2 Wrapper -->
          </div>
          <!-- End of Header -->
        </div>
        
        <div class="dbfl marT10 wid980">
          <div class="lp_left dbfl wid980">
		  <div class="wid980 adv_search_rgt">
                  <div class="adv_search_lft wid300 marL250">  
						<div class="error_msg"></div>
					</div>
			</div>
		  
                <div class="wid980 adv_search_rgt">
                 <?php
                   if($_POST['advsearch'])
                      {
                        $query = "Select e.event_name, e.description, e.event_id from event as e, categories as c where e.event_type='public' and e.category_id = c.category_id ";
                        $q = "";
                        $categoryQuery = "";
                        $count = 0;
                        if(!empty($_POST['categories']))
                            {
                              $categories = $_POST['categories'];
                              $N = count($categories);
                              //echo("You selected $N categories(s): ");
                              for($i=0; $i < $N; $i++)
                               {
                                  //echo($categories[$i] . " ");
                                  $temp = $categories[$i];
                                  if($count == 0)
                                    {
                                      $categoryQuery .= " c.category_name = " ."'" .$temp ."'" ;
                                    }
                                  else
                                    {
                                     $categoryQuery .= " or c.category_name = " ."'" .$temp ."'" ; 
                                    }
                                  $count++;
                                }
                              if($count == 1)
                                {
                                  $query = $query ."and " .$categoryQuery;    
                                }
                              else
                                {  
                                  $query = $query ." and (" .$categoryQuery .")";
                                }
                              //echo "the query looks like: " .$query;
                          }
                        if(!empty($_POST['date']))
                          {
                            $date=$_POST['date'];
                            //echo $date;
                            $dateQuery = " and e.date = " ."'" .$date ."'" ;
                            $query = $query . $dateQuery;
                            //echo "date query : " .$query;
                          }
                        if(!empty($_POST['city']))
                          {
                            $city=$_POST['city'];
                            if($city != 'default')
                              {  
                                //$city=$_POST['city'];
                                //echo $city;
                                $cityQuery = " and e.city = " ."'" .$city ."'" ;
                                $query = $query . $cityQuery;
                                // echo "city query : " .$query;
                              }
                          }
                        if(!empty($_POST['zipcode']))
                          {
                            $zipcode=$_POST['zipcode'];
                           // echo $zipcode;
                            $zipcodeQuery = " and e.zip = " ."'" .$zipcode ."'";
                            $query = $query . $zipcodeQuery;
                            //echo "zipcode query : " .$query;
                          }
                        if(!empty($_POST['HH']))
                          {
                            $HH=$_POST['HH'];
                            if($HH != "default")
                              {
                                $time = $HH.":";
                                $temp = $HH + 03;
                                $forwardTime = $temp.":";
                                //echo " time is: " .$time;
                                //$query = $query . $zipcodeQuery;
                                if(!empty($_POST['MM']) and $_POST['MM'] != "default")
                                  {
                                    $MM=$_POST['MM'];
                                    $time .= $MM.":00";
                                    $forwardTime .= $MM.":00";
                                    //$timeQuery = " and e.time = " ."'" .$time ."'";
                                     $timeQuery = " and e.time BETWEEN " ."'" .$time ."' and " ."'" .$forwardTime ."'";
                                     $query = $query . $timeQuery;
                                    
                                    //echo " aaaaaa " .$query;
                                    //echo " time is: " .$MM;
                                  }
                                else
                                  {
                                    $time .= "00:00";
                                    $forwardTime .= "00:00";
                                    $timeQuery = " and e.time BETWEEN " ."'" .$time ."' and " ."'" .$forwardTime ."'";
                                    $query = $query . $timeQuery;
                                    //echo " aaasdsdfaaa " .$query;
                                  }
                              }
                          }
                        
                        /*else{
                          echo "No categories selected";
                        }*/
                      
                          //echo "the data is: " .$input_career;
                          $dbHost = "silo.cs.indiana.edu";
                          $dbUserAndName = "b561f12_63";
                          $dbPass = "b561f12_63";

                          $con = mysql_connect ($dbHost, $dbUserAndName, $dbPass);

                          if($con)
                            {
                              //echo "Connected!!";
                              //Querying the database for fetching the search results
                              mysql_select_db("b561f12_63", $con);

                              $result = mysql_query("$query") or die ("Error in query: ".mysql_error());
                              if (mysql_num_rows($result) == 0)
                                {
                                  echo "No results found!";
                                }
                              //echo "the data is: ".$input;
                              else
                                {
                                  $count = 0;
                                  
                                  while($row = mysql_fetch_array($result))
                                    {
                                      if($count == 0)
                                        {
                                          echo '<div class="wid980 hgt25"><p class="bold font20 fontOrange"><a href="display.php?eventId=' . $row[2] . '">' .$row[0] .'</a></p></div>';
                                          echo "<div class='wid980 hgt75'><p class='font15 fontColor'>" . $row[1] . "</p></div>";
                                        }
                                      
                                      else
                                        {
                                          echo "<hr style='background:LIGHTGREY;'>";
                                          echo '<div class="wid980 hgt25"><p class="bold font20 fontOrange"><a href="display.php?eventId=' . $row[2] . '">' .$row[0] .'</a></p></div>';
                                          //echo "<td>" . $row[1] . "</td>";
                                          echo "<div class='wid980 hgt75'><p class='font15 fontColor'>" . $row[1] . "</p></div>";
                                        }
                                      $count++;
                                    }
                                  mysql_close($con);
                                }
                            }
                          else
                            echo "Couldn't Connect!!!";
                       }
                    ?>
          </div>
        </div>
        </div>
    </div>
    <div class="footerarea">
      <div class="footer">
        <div class="copyright dbfl">
          <div class="footerlinks dbfl"><span class="dbfr"><a href="#">About Us</a> | <a href="#">About Event Spotting</a> | <a href="#">Contact Us</a> | <a href="#">Help</a></span></div>
          </div>
        <div class="connectwithus_links dbfl wid250">
          <div class="dbfl wid250">
            <h1>CONNECT WITH US</h1>
            <!--<p>On various Social networking Platforms</p>-->
          </div>
          <span class="dbfl wid250"> <a href="#." class="fbico">Facebook</a><a href="#." class="twitterico">Twitter</a> </span> </div>
      </div>
    </div>
  </div>
</div>
<script type="text/javascript">
	//<![CDATA[
        var validator = new FormValidator(
            'signupForm',
            [ { name: 'name', display: 'Name', rules: 'required' },
              { name: 'email', display: 'Email', rules: 'required|valid_email' },
			  //{ name: 'interests', display: 'Interests', rules: 'required' },
              { name: 'password', rules: 'required' },
			  { name: 'password', display: 'Password', rules: 'min_length[6]' },
              { name: 'repassword', display: 'password confirmation', rules: 'required|matches[password]' }
			  	
            ], 
            function(errors, event) { 
            	var ERRORS = $('.error_msg');
        					    
        		if (errors.length > 0) {
        			ERRORS.empty();
            		
            		for (var i = 0, errorLength = errors.length; i < errorLength; i++) {
            			ERRORS.append(errors[i].message + '<br />');
        			}
        							
        			ERRORS.fadeIn(200);
    			} 
     		}
     	);
    //]]>
    </script>
</body>
</html>
