<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
 <meta name="viewport" content="width=device-width, initial-scale=1">
   <link rel="stylesheet" href="main_user.css">
  <style>
  .header-nav-right{
  display:flex;
  
  
  }
 .header-user-name{
  padding-left: 20px;
    padding-top: 20px;
    color: white;
    font-weight: bolder;
  }
  .header-log-out-logo{
  /* padding-right: 35px;
  padding-top: 20PX; */
      padding: 17px;
   /* background-image: url("log-out.png"); */
  }
  .logout-btn{
   background: linear-gradient(154deg,#008fe2,#00b29c);
   cursor:pointer;
  }
  .wolet-page{
  padding-left: 20px;
    padding-top: 20px;
    color: white;
    cursor: pointer;
    }
    .hy-wpage{
    color: white;
    font-weight: bolder;
    }
   
    .user-r-page{
     padding-left: 20px;
    padding-top: 20px;
    color: white;
    cursor: pointer;
    }
    
    .hy-user{
    color: white;
    font-weight: bolder;
    
    }
    .user-r-history{
     padding-left: 20px;
    padding-top: 20px;
    color: white;
    cursor: pointer;
    }
    .hy-history{
    color: white;
    font-weight: bolder;
    }
    </style>

<title>Online Parking User</title>
<script
	src="jquery.min.js"></script>

 	<script type="text/javascript" src="user.js"></script>
 	<script type="text/javascript" src="header.js"></script>
	
	
  <link rel="stylesheet" href="bootstrap.min.css">
   <script type="text/javascript" src="bootstrap.min.js"></script>
	<body>
 <div class="main-container">
			 <div id="hdr" class="header"><!-- vw<input type="button" value="myWolet" onclick="window.location = 'path-here';"> --> </div>
			<!-- <script language="javascript" type="text/javascript" 
  src="header.html"></script> -->
			
       <div class="body">
       <div class="mainId">
        <div class="select-city-area">
           <div id="cityDiv">
				<lebel id="lebel">Select city:</lebel> 
				<select class="selectSize" name="city" id="city">
				  <option  value=''>Select City</option>
				</select><br>
			</div>
		<div>
			<label id="lebel">Select area:</label> 
			<select class="selectSize" name="area"id="area" >
			<option selected="selected" value="0">Select area</option></select>
		</div>
		
       <div id="inTime" >
         <lebel id="lebel"">Expected Outtime :</lebel>
         <select onclick="timeFunction()" class="selectSize">
			<option selected="selected" value="0">Duration</option>
			<option  value="0"2>02:00</option>
			<option  value="03">03:00</option>
			<option  value="04">04:00</option>
			<option  value="05">05:00</option>
			<option  value="06">06:00</option>
			<option  value="07">07:00</option>
			<option  value="08">08:00</option>
			<option  value="09">09:00</option>
			<option  value="10">10:00</option>
			<option  value="11">11:00</option>
			<option  value="12">12:00++</option>
			
	     </select>
	   </div> 
	</div>
	
	  </div>
	
	  <p>Please Click Register Button and get Slot After click select slot and book.</p>
	  <div >
	 <div class="btn-slot">
	 	<div>
	 		 <button class="register-button" onclick="getSlot('park')">Register Slot</button>
	 		 </div>
	 	 <div>
	 	 	<button class="btn-cancel" onclick="onRefres()">Cancel</button>
	 	 </div>
	  </div><br>
	  <div id="demo"></div>
	  <div class="rg-button">
	  <button class="book-now" type="button"   onclick="bookNow()">Book Now</button>
	  
	  
	  </div>
    </div>
 </div>
 
</div>
<!-- Modal -->
 <div class="modal fade" id="getCodeModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
   <div class="modal-dialog modal-lg">
      <div class="modal-content">
       <div><button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button></div>
       <div class="modal-header">
       <div class="m-header">
       <h4 class="modal-title" id="myModalLabel"> Onlineparking Wolet </h4></div>
       </div>
       
         <div class="modal-content-parking">
         <div id="modal-info">
         	
         
         </div>
         
         </div>
         <!-- style="overflow-y: scroll;" -->
      <!--  <div class="modal-body" id="getCode"> -->
       
      
       </div>
    </div>
   </div>
 </div>
</body>





</html>