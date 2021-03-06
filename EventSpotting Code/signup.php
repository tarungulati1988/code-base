<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Event Spotting</title>
<link href="style.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="/classpath/js/jquery-1.3.2.min.js"></script>
<script type="text/javascript" src="/classpath/js/jquery.pngFix.js"></script>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.6.4/jquery.min.js"></script>
<script type="text/javascript" src="validate.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$(document).pngFix();
	});
</script>
<script type="text/javascript" src="/classpath/js/mainnav2.js"></script>
</head>
<body>
<div id="bodyWrap">
  <div class="wrapper">
    <div class="toplinks wid980"> <span class="dbfr padTB5">
	<?php
	include 'username.php';
	?>
	</a></span> </div>
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
                <li class="menu1" ><a href="home.php" class="current1" id="u1">Home</a></li>
                <li class="menu1"><a href="event.php" id="u2"> Events</a></li>
                <li class="menu1"><a href="pastevent.php" id="u2"> Past Events</a></li>
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
          <div class="lp_left dbfl wid980 height500">
		  <div class="wid980 adv_search_rgt">
                  <div class="adv_search_lft wid300 marL250">  
						<div class="error_msg"></div>
					</div>
			</div>
		  <form name="signupForm" action="signupphp.php" method="post">
            <!--<img src="calendar.jpg" height="379" width="579" alt="events" class="lpleft_image"/>-->
			<!--Name code div starts-->
                <div class="wid980 adv_search_rgt">
                  <div class="adv_search_lft wid300 marL250">  
                    <p class="bold marT30 font15">Name: </p>
                  </div>
                  <div class="adv_search_rgt wid300">  
                    <p class="bold marT30 font15">
                      <input type="text" name="name" class="adv_search_zipcode"/>
                    </p>
                  </div>
                </div>
                <!--Name code ends-->
				<!--email code div starts-->
                <div class="wid980 adv_search_rgt">
                  <div class="adv_search_lft wid300 marL250">  
                    <p class="bold marT30 font15">Email: </p>
                  </div>
                  <div class="adv_search_rgt wid300">  
                    <p class="bold marT30 font15">
                      <input type="text" name="email" class="adv_search_zipcode"/>
                    </p>
                  </div>
                </div>
                <!--email code ends-->
                <div class="wid980 adv_search_rgt">
                  <div class="adv_search_lft wid300 marL250">  
                      <p class="bold marT30 font15">Interests: </p>
                  </div>
                  <div class="adv_search_rgt wid190">  
                    <p class="bold marT30 font15">
                      <input type="checkbox" name="interests[]" value="10" id="Art Exhibitions">Art Exhibitions<br></input>
                      <input type="checkbox" name="interests[]" value="8" id="Career Fairs">Career Fairs<br></input>
                      <input type="checkbox" name="interests[]" value="2" id="Concerts">Concerts<br></input>
                      <input type="checkbox" name="interests[]" value="3" id="Plays">Plays<br></input>
                      <input type="checkbox" name="interests[]" value="6" id="Sports">Sports<br></input>
                    </p>
                  </div>
                  <div class="adv_search_rgt wid190">  
                    <p class="bold marT30 font15">
                      <input type="checkbox" name="interests[]" value="1" class="marL10" id="Business">Business<br></input>
                      <input type="checkbox" name="interests[]" value="9" class="marL10" id="Charity">Charity<br></input>
                      <input type="checkbox" name="interests[]" value="4" class="marL10" id="Parties">Parties<br></input>
                      <input type="checkbox" name="interests[]" value="5" class="marL10" id="Reunions">Reunions<br></input>
                      <input type="checkbox" name="interests[]" value="7" class="marL10" id="Tech Talks">Tech Talks<br></input>
                    </p>
                  </div>
                </div>
                <!--password code div starts-->
                <div class="wid980 adv_search_rgt">
                  <div class="adv_search_lft wid300 marL250">  
                    <p class="bold marT30 font15">Password: </p>
                  </div>
                  <div class="adv_search_rgt wid300">  
                    <p class="bold marT30 font15">
                      <input type="password" name="password" class="adv_search_zipcode"/>
                    </p>
                  </div>
                </div>
                <!--password code ends-->
				<!--Reenter password code div starts-->
                <div class="wid980 adv_search_rgt">
                  <div class="adv_search_lft wid300 marL250">  
                    <p class="bold marT30 font15">Re-enter Password: </p>
                  </div>
                  <div class="adv_search_rgt wid300">  
                    <p class="bold marT30 font15">
                      <input type="password" name="repassword" class="adv_search_zipcode"/>
                    </p>
                  </div>
                </div>
                <!--reenter password code ends-->
                
            <div class="wid980 adv_search_rgt">
                <p class="bold marT30 font15"><input type="submit" value="Sign up!"   class="searchbtn center_align orange"/></p>
              </form>
            </div>
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
