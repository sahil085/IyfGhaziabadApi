<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: root
  Date: 24/6/18
  Time: 11:34 AM
  To change this template use File | Settings | File Templates.
--%>
<jsp:include page="header.jsp"></jsp:include>


<div class="page-head">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title">
                    The goals of IYF are as follow:
                </h1>
                <h6>
                <ul>
                    <li>Training in soft skills like Public Speaking, Stress management, Leadership and Management by the application of the timeless wisdom in the modern scenario.
                    </li>
                    <br>
                    <li>Bringing out the potential in the individual like Concentration, Motivation, Self Confidence etc.
                    </li>
                </ul>
                </h6>
            </div>
        </div>
    </div>
</div>

<div class="page-head" style="margin-top:-2% ">
    <div class="container">
        <div class="row">
            <div class="page-head-content">
                <h1 class="page-title">
                    Why Join Us ?
                </h1>
                <h6>
                    <ul>
                        <li>
                            Get Notified By Mail Of Upcoming Events, Festivals And Seminars.
                        </li>
                        <li>
                            Online Seminars And Courses.
                        </li>
                        <li>
                            Stay Live With Us.
                        </li>
                        <li>
                            Online Seat Booking For Seminar And Vedic Courses.
                        </li>
                        <li>
                            Ask Your Query Online.
                        </li>
                    </ul>
                </h6>
            </div>
        </div>
    </div>
</div>

<div class="content-area submit-property" style="background-color: #FCFCFC;">&nbsp;
    <div class="container">
        <div class="clearfix" >
            <div class="wizard-container">

                <div class="wizard-card ct-wizard-orange" id="wizardProperty">
                    <form action="/account/register" method="post">
                        <div class="wizard-header">
                            <h3>
                                <b>Join </b> Us Today <br>
                                <small style="color: black">
                                    Get Yourself Register Here..!
                                </small>
                            </h3>
                        </div>

                        <ul>
                            <li><a href="#step1" data-toggle="tab">Step 1</a></li>
                            <li><a href="#step2" data-toggle="tab">Step 2</a></li>
                            <li><a href="#step3" data-toggle="tab">Step 3 </a></li>
                            <li><a href="#step4" data-toggle="tab">Finished </a></li>
                        </ul>

                        <div class="tab-content">

                            <div class="tab-pane" id="step1">
                                <div class="row p-b-15  ">
                                    <h4 class="info-text"> Let's start with the basic information</h4>
                                    <div class="col-sm-4 col-sm-offset-1">
                                        <div class="picture-container">
                                            <div class="picture">
                                                <img src="https://lh3.googleusercontent.com/-wia-03mMF9o/WrYixMPdC2I/AAAAAAAA8d8/J88ffm1w-Z8NBm0KeFzgGOMENPMxDdq6wCCoYBhgL/s1024/ISKCON%2BVrindavan%2BDeity%2BDarshan%2B24%2BMar%2B2018%2B%252818%2529.jpg" width="100px" class="picture-src" id="wizardPicturePreview" title=""/>
                                                <input type="file" id="wizard-picture">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>User Name <small>(required)</small></label>
                                            <input name="username" type="text"  class="form-control" placeholder="Enter Your Name"  required>
                                        </div>

                                        <div class="form-group">
                                            <label>Email <small>(required)</small></label>
                                            <input name="email" type="email" class="form-control" placeholder="Enter Your Email">
                                        </div>
                                        <div class="form-group">
                                            <label>Password <small>(required)</small></label>
                                            <input name="password" type="password" class="form-control" placeholder="Create Your Password">
                                        </div>

                                        <div class="form-group">
                                            <label>Gender :</label>
                                            <select required name="gender" id="lunchBegins" class="selectpicker" data-live-search="true" data-live-search-style="begins" title="Select Your Gender">
                                                <option>Male</option>
                                                <option>Female</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--  End step 1 -->

                            <div class="tab-pane" id="step2">
                                <div class="row p-b-15  ">
                                    <h4 class="info-text"> Contact information</h4>
                                    <div class="col-sm-4 col-sm-offset-1">
                                        <div class="picture-container">
                                            <div class="picture">
                                                <img src="https://lh3.googleusercontent.com/-wia-03mMF9o/WrYixMPdC2I/AAAAAAAA8d8/J88ffm1w-Z8NBm0KeFzgGOMENPMxDdq6wCCoYBhgL/s1024/ISKCON%2BVrindavan%2BDeity%2BDarshan%2B24%2BMar%2B2018%2B%252818%2529.jpg" width="100px" class="picture-src" id="wizardPicturePreview" title=""/>
                                                <input type="file" id="wizard-picture">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Mobile Number<small> (required)</small></label>
                                            <input name="mobileNumber" type="number" minlength="10" maxlength="10" class="form-control" placeholder="Enter Your Mobile Number"  required>
                                        </div>

                                        <div class="form-group">
                                            <label>Alternate Mobile Number</label>
                                            <input name="alternateMobileNumber" type="number" minlength="10" maxlength="10" class="form-control" placeholder="Enter Your Alternate Mobile Number">
                                        </div>
                                        <div class="form-group">
                                            <label>Your Current Address <small> (required)</small></label>
                                            <input name="currentAddress" type="text" class="form-control" placeholder="Enter Your Current Address" required>
                                        </div>

                                        <div class="form-group">
                                            <label>Your Permanent Address<small> (required)</small></label>
                                            <input name="permanentAddress" type="text" class="form-control" placeholder="Enter Your Permanent Address" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Your City<small> (required)</small></label>
                                            <input name="city" type="text" class="form-control" placeholder="Enter Your City" required>
                                        </div>
                                        <div class="form-group">
                                            <label>Your Street<small> (required)</small></label>
                                            <select required name="street" id="lunchBegin" class="selectpicker" data-live-search="true" data-live-search-style="begins" title="Select Your Stree">
                                                <option>Pillakhua</option>
                                                <option>Raj Nagar</option>

                                            </select>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- End step 2 -->

                            <div class="tab-pane" id="step3">
                                <div class="row p-b-15  ">
                                    <h4 class="info-text"> Personal information</h4>
                                    <div class="col-sm-4 col-sm-offset-1">
                                        <div class="picture-container">
                                            <div class="picture">
                                                <img src="https://lh3.googleusercontent.com/-wia-03mMF9o/WrYixMPdC2I/AAAAAAAA8d8/J88ffm1w-Z8NBm0KeFzgGOMENPMxDdq6wCCoYBhgL/s1024/ISKCON%2BVrindavan%2BDeity%2BDarshan%2B24%2BMar%2B2018%2B%252818%2529.jpg" width="100px" class="picture-src" id="wizardPicturePreview" title=""/>
                                                <input type="file" id="wizard-picture">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6">
                                        <div class="form-group">
                                            <label>Nearest Iskcon Temple<small>(required)</small></label>
                                            <select required name="nearestIskconTemple" id="unchBegins" class="selectpicker" data-live-search="true" data-live-search-style="begins" title="Select Your Nearest Iskcon Temple">
                                                <option> Iskcon Ghaziabad</option>
                                                <option> Iskcon Delhi</option>
                                            </select>
                                        </div>

                                        <div class="form-group">
                                            <label>Your Facilitator Name<small> (Optional)</small></label>
                                            <select required name="FacilitatorName" id="lunchBegins" class="selectpicker" data-live-search="true" data-live-search-style="begins" title="None-Selected">
                                                <option>HG Charu Govind Prabhu Ji</option>
                                                <option>HG Sarv Mangal Prabhu Ji</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>Initiated <small>(Are You Deekshit ?)</small></label>
                                            <select required name="isInitiated" id="lunchBegins" class="selectpicker" data-live-search="true" data-live-search-style="begins" title="None-Selected">
                                                <option>Yes</option>
                                                <option>No</option>
                                            </select>
                                        </div>
                                        <div class="form-group">
                                            <label>How Many Rounds You Chant<small> (Optional)</small></label>
                                            <input name="roundsChant" type="number" class="form-control" placeholder="How Many Rounds You Chant" required>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!--  End step 3 -->


                            <div class="tab-pane" id="step4">
                                <h4 class="info-text"> Finished and submit </h4>
                                <div class="row">
                                    <div class="col-sm-12">
                                        <div class="">
                                            <p>
                                                <label><strong>Terms and Conditions</strong></label>
                                                I HereBy Accept That I Will Not Post Bad Comment And Always Humble Between Devotess Of Iskcon
                                            </p>

                                            <div class="checkbox">
                                                <label>
                                                    <input type="checkbox" required/> <strong>Accept termes and conditions.</strong>
                                                </label>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                            </div>
                            <!--  End step 4 -->

                        </div>

                        <div class="wizard-footer">
                            <div class="pull-right">
                                <input type='button' class='btn btn-next btn-primary' name='next' value='Next' />
                                <input type='submit' class='btn btn-finish btn-primary ' name='finish' value='Finish' />
                            </div>

                            <div class="pull-left">
                                <input type='button' class='btn btn-previous btn-default' name='previous' value='Previous' />
                            </div>
                            <div class="clearfix"></div>
                        </div>
                    </form>
                </div>
                <!-- End submit form -->
            </div>
        </div>
    </div>
</div>


<jsp:include page="footer.jsp"></jsp:include>

<c:if test="${not empty errMessage}">
    <script>
        function randomToast ()
        {
            var priority = 'danger';
            var title    = 'danger';
            var message  = ${message};

            $.toaster({ priority : priority, title : title, message : message });
        }
    </script>
</c:if>
