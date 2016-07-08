<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<head>
<style>
#addmodal {padding-top:10%;}
</style>
</head>
<html>
<body class="hold-transition skin-blue sidebar-mini">
<div class="wrapper">

  
  <%@ include file="zoyheader.jsp"%>
  <%@ include file="zoysidebar.jsp"%>

  
  <!-- Content Wrapper. Contains page content -->
  <div class="content-wrapper">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        Home Page
        <small>Control panel</small>
      </h1>
      <ol class="breadcrumb">
        <li><a href="#"><i class="fa fa-dashboard"></i> Official Website</a></li>
        <li class="active">Home Page</li>
      </ol>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="row">
        <div class="col-xs-12">

          <div class="box">
            <div class="box-header">
              <h3 class="box-title">Settings of Home Page </h3>&nbsp;&nbsp;&nbsp;&nbsp;
              <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#addmodal">Add Setting</button>
            </div>
            <!-- /.box-header -->
            <div class="box-body">
                <table id="homelisttable" class="table table-bordered table-striped">
                  <thead>
                  <tr>
                    <th>Setting ID</th>
                    <th>Page ID</th>
                    <th>Short Info</th>
                    <th>Content</th>
                    <th>Modified Time</th>
                  </tr>
                  </thead>
                  <tfoot>
                  <tr>
                    <th>Setting ID</th>
                    <th>Page ID</th>
                    <th>Short Info</th>
                    <th>Content</th>
                    <th>Modified Time</th>
                  </tr>
                  </tfoot>

                </table>
            </div>
            <!-- /.box-body -->
          </div>
          <!-- /.box -->
        </div>
        <!-- /.col -->
      </div>
      <!-- /.row -->

      <!-- add setting（Modal） -->
      <div class="modal fade" id="addmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
         <div class="modal-dialog">
            <div class="modal-content">
               <div class="modal-header">
                  <button type="button" class="close" 
                     data-dismiss="modal" aria-hidden="true">
                        &times;
                  </button>
                  <h4 class="modal-title" id="myModalLabel">
                     Add Setting Dialog
                  </h4>
               </div>
               <div class="modal-body">
                <p class="bg-success" id="addresponsetext"></p>
                  <form id="addform">  
                    <div class="form-group">
                      <label for="addsettingid" class="form-control-label">Setting ID:</label>
                      <input type="text" class="form-control" id="addsettingid">
                    </div>
                    <div class="form-group">
                      <lable for="addshortinfo" class="form-control-label">Short Info:</label>
                      <input type="text" class="form-control" id="addshortinfo">
                    </div>
                    <div class="form-group">
                      <lable for="addcontent" class="form-control-label">Content:</label>
                      <input type="text" class="form-control" id="addcontent">
                    </div>
                  </form>
               </div>
               <div class="modal-footer">
                  <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Close</button>
                  <button type="button" class="btn btn-primary" id="addsubmitbtn">Submit</button>
               </div>
            </div><!-- /.modal-content -->
      </div><!-- /.modal -->


    </section>
    <!-- /.content -->
    
  </div>


  <%@ include file="zoyfooter.jsp"%>
  <%@ include file="zoysetsidebar.jsp"%>

  <!-- /.control-sidebar -->
  <!-- Add the sidebar's background. This div must be placed
       immediately after the control sidebar -->
  <div class="control-sidebar-bg"></div>
</div>
<!-- ./wrapper -->

<script>
$(function () {
    $("#homelisttable").DataTable({
      "processing": true,
      "serverSide": true,
      "ajax":{
        "url":"getjsondata",
        "type": "POST",
        "dataType":"text",
        "data":{
          "params":"home"
        }
      },
      "columns": [
            { "data": "setid"},
            { "data": "pageid"},
            { "data": "note"},
            { "data": "content"},
            { "data": "updatetime"}
        ]
    });
    $("#addresponsetext").hide();
    $("#addsubmitbtn").click(function(){
      $("#addresponsetext").hide();
      $.ajax({
        url:"addsetting",
        type:"post",
        dataType:"text",
        data:{
          setid:$("#addsettingid").val(),
          pageid:"home",
          note:$("#addshortinfo").val(),
          content:$("#addcontent").val()
        },
        success:function(responseText){
          if (responseText=="1") {
            $("#addresponsetext").html("Save Success!");
            $("#addresponsetext").show();    
            $("#homelisttable").DataTable();     
          } else {
            $("#addresponsetext").html("Save Error!");
            $("#addresponsetext").show();   
          }
        },
        error:function(){
          $("#addresponsetext").html("Save Error!");
          $("#addresponsetext").show();  
        }
      });
    });
  });
  $.widget.bridge('uibutton', $.ui.button);
  
</script>
</body>
</html>
