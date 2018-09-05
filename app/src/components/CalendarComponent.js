import React, {Component} from 'react';
import FullCalendar from 'fullcalendar-reactwrapper';
import 'fullcalendar-reactwrapper/dist/css/fullcalendar.min.css';
import moment from 'moment';

class CalendarComponent extends Component {
    constructor(props) {
        super(props);
        console.log("Inside CalendarComponent constructor");
        this.state = {
            options: {
                header: {
                    left: '',
                    center: '',
                    right: ''
                },
                defaultView: 'agendaWeek',
                minTime: "08:00:00",
                maxTime: "22:00:00",
                allDaySlot: false,
                hiddenDays: [6],
                defaultDate: moment('2018-04-01')
            },
            events: []
        }
    }


    componentWillReceiveProps(nextProps) {
        console.log('Inside CalendarComponent shouldComponentUpdate');
        // console.log(this.props);
        // console.log(nextProps);
        if (JSON.stringify(this.props) === JSON.stringify(nextProps)){
            return false;
        }
        this.props = nextProps;
        let day, time, event, updatedEvents;
        let determineDay = function (day) {
            if (day === "Sunday") return '2018-04-01';
            else if (day === "Monday") return '2018-04-02';
            else if (day === "Tuesday") return '2018-04-03';
            else if (day === "Wednesday") return '2018-04-04';
            else if (day === "Thursday") return '2018-04-05';
            else if (day === "Friday") return '2018-04-06';
        };
        console.log(this.props);
        day = determineDay(this.props.courseObject[0].day);
        time = this.props.courseObject[0].hours.split(['-']);
        event = {
            title: `Course Number: ${this.props.courseObject[0].courseGroupId.courseNum},
                Location: ${this.props.courseObject[0].courseGroupId.groupLocation}`,
            start: `${day}T${time[0]}`,
            end: `${day}T${time[1]}`
        };
        updatedEvents = [...this.state.events];
        updatedEvents.push(event);
        console.log(updatedEvents);
        this.setState ({events: updatedEvents});
        console.log(this.state);
        return true;
    }

    // componentDidUpdate(){
    //     console.log("Inside CalendarComponent componentDidUpdate");
    //     let day, time, event, updatedEvents;
    //         let determineDay = function (day) {
    //             if (day === "Sunday") return '2018-04-01';
    //             else if (day === "Monday") return '2018-04-02';
    //             else if (day === "Tuesday") return '2018-04-03';
    //             else if (day === "Wednesday") return '2018-04-04';
    //             else if (day === "Thursday") return '2018-04-05';
    //             else if (day === "Friday") return '2018-04-06';
    //         };
    //         console.log(this.props);
    //         day = determineDay(this.props.courseGroupObject[0].day);
    //         time = this.props.courseGroupObject[0].hours.split(['-']);
    //         event = {
    //             title: `Course Number: ${this.props.courseGroupObject[0].courseGroupId.courseNum},
    //             Location: ${this.props.courseGroupObject[0].courseGroupId.groupLocation}`,
    //             start: `${day}T${time[0]}`,
    //             end: `${day}T${time[1]}`
    //         };
    //         updatedEvents = [...this.state.events];
    //         updatedEvents.push(event);
    //         console.log(updatedEvents);
    //         this.setState ({events: updatedEvents})
    // }

    render () {
        console.log("inside CalendarComponent render");
        console.log(this.state);
        let {events, options} = this.state;
        return (
            <FullCalendar
                {...options}
                events={events}
            />
        )
    }
}

export default CalendarComponent;

