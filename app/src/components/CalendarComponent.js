import React, {Component} from 'react';
import FullCalendar from 'fullcalendar-reactwrapper';
import 'fullcalendar-reactwrapper/dist/css/fullcalendar.min.css';


const calendarComponent = (props) => {
    console.log(props.courseObject);
    let time, events;
    if (props.courseObject.length !== 0){
        time = props.courseObject[0].hours.split(['-']);
        events = [
            {
                title: `Course Number: ${props.courseObject[0].courseGroupId.courseNum},
                Location: ${props.courseObject[0].courseGroupId.groupLocation}`,
                start: `2018-08-13T${time[0]}`,
                end: `2018-08-13T${time[1]}`
            }
        ]

    }
    console.log(time);



    const options = {
        header: {
            left:   'title',
            center: '',
            right:  'prev,next'
        },
        defaultView: 'agendaWeek',
        minTime: "08:00:00",
        maxTime: "22:00:00",
        allDaySlot: false,
        hiddenDays: [6]
    }

    return (
        <FullCalendar
            {...options}
            events = {events}
        />
    )
}

export default calendarComponent;

