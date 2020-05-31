/*
import * as $ from 'jquery';
window.jQuery = $;
window.$ = $;

var myEvents = [
  { name: "New Year", date: "Wed Jan 01 2020 00:00:00 GMT-0800 (Pacific Standard Time)", type: "holiday", everyYear: true },
  { name: "Valentine's Day", date: "Fri Feb 14 2020 00:00:00 GMT-0800 (Pacific Standard Time)", type: "holiday", everyYear: true },
  { name: "Birthday", date: "February/3/2020", type: "birthday" },
  { name: "Author's Birthday", date: "February/15/2020", type: "birthday", everyYear: true },
  { name: "Holiday", date: "February/15/2020", type: "event" }
];

var dateBinderElement=null;
window.greet = function greet(element) {
  dateBinderElement=element;
};

$( document ).ready(function() {
  var calElem= $('#todo-calendar');
  var evoCal = calElem.evoCalendar({
    /!*calendarEvents: myEvents,*!/
    format: 'mm/dd/yyyy',
    titleFormat: 'MM yyyy',
    eventHeaderFormat: 'MM d, yyyy',
    todayHighlight: true,
    sidebarToggler: true,
    sidebarDisplayDefault: false,
    eventListToggler: false,
    eventDisplayDefault: false,
    onSelectDate:function (event) {
      var date= $(event.target).data('date-val');
      console.log("dateSelected.............:: "+ $(event.target));
      dateBinderElement.$server.setData(date);
    }
  });
});

*/
