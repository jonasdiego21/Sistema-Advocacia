/* layout.js */
$(document).ready(function() {
	$(this).ajaxSend(function(event, jqxhr, settings) {
		var token = $("meta[name='_csrf']").attr("content");
		var header = $("meta[name='_csrf_header']").attr("content");
	});
	
    mf_base.doInit();
});

var mf_base = function() {
    
    var initMainContainer = function() {
        $(".main").height($("body").height() - $("header").height());
    }
    
    var initSideBar = function() {
        try {           			
            $('.collapsible').collapsible();

            $(".button-collapse-minimalize").click(function() {
            	$("header, nav, main, .side-nav, .side-nav i, .side-nav i").removeClass("no-transition");
            	$(".side-nav, main, header, footer").toggleClass("smaller");
            	Cookies.set("mf-base-sidebar-smaller", $(".side-nav").hasClass("smaller"));
            });
            
            var smaller = Cookies.get("mf-base-sidebar-smaller") === "true";
            if(smaller) {
            	$(".side-nav, main, header, footer").toggleClass("smaller");
            }
        } catch(err) {
            console.log(err);
        }	  
    }

    var showAlert = function(text, type, delayAmount, delay) {
    	var icon = getIconByType(type);
		var color = getColorByType(type);
		var content = $('<div class="valign-wrapper"><i class="material-icons ' + color + '-text">' + icon + '</i><span class="' + color + '-text">' + text + '</span></div>');

    	setTimeout(function() {    			
    		Materialize.toast(content, delay, 'alert-' + type + ' rounded');
		}, delayAmount);
    }

    var initDataTableInElement = function(el, setts) {
        var orderCols = (el.data("sort-col") == undefined)? [] : el.data("sort-col").toString().split(",");
        var orderDirs = (el.data("sort-direction") === undefined)? [] : el.data("sort-direction").toString().split(",");
        var order = [];

        orderCols.forEach(function(col, i) {
            if(!isNaN(parseInt(col))) {
                order.push([parseInt(col), orderDirs[i]]);
            }
        });
        
        return dataTable;
    }

    return {
        
        doInit : function() {
            initMainContainer();
            initSideBar();           
        },
        
        doAddChart : function(el, type, labels, datasets) {
        	var ctx = el[0];
			return new Chart(ctx, {
			    type: type,
			    data: {
			        labels: labels,
			        datasets: datasets
			    },
			    options: { 
			        elements: {
				        line: {
				        }, 
				        point: {
					    	radius: 7
				        }
			        }
			        
			    }
			});
			
        }, 
        
        doAddDataTable : initDataTableInElement, 

        doAlertSet : function(alertSet) {        	
        	if(typeof alertSet === '[object Array]') {
	            for(k in alertSet) {
	                var alert = alertSet[k];
	                showAlert(alert.message, alert.type.toLowerCase(), 0, alert.delay);
	            }
        	} else {
        		if (alertSet != null){        			
        			showAlert(alertSet.message, alertSet.type.toLowerCase(), 0, alertSet.delay);
        		}
        	}
        }, 
        
        doShowAlert : showAlert, 
        
        doGetCurrentDate : function() {
        	var today = new Date();
        	var dd = today.getDate();
        	var mm = today.getMonth()+1;
        	var yyyy = today.getFullYear();

        	if(dd<10) {
        	    dd='0'+dd
        	} 

        	if(mm<10) {
        	    mm='0'+mm
        	} 

        	today = mm + '/' + dd + '/' + yyyy;
        	return today;
        }
        
    };
    
}();