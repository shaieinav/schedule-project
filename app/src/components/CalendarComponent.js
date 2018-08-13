import React from 'react';
import FullCalendar from 'fullcalendar-reactwrapper';
import 'fullcalendar-reactwrapper/dist/css/fullcalendar.min.css';


const calendarComponent = (props) => {

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
        />
    )
}

export default calendarComponent;

