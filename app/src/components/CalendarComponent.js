import React, {Component} from 'react';
import FullCalendar from 'fullcalendar-reactwrapper';
import 'fullcalendar-reactwrapper/dist/css/fullcalendar.min.css';
import moment from 'moment';

class CalendarComponent extends Component {

    constructor(props) {

        super(props);

        this.state = {
            options: {
                header: {
                    left: '',
                    center: '',
                    right: ''
                },
                defaultView: 'agendaWeek',
                minTime: "08:00:00",
                maxTime: "23:00:00",
                allDaySlot: false,
                hiddenDays: [6],
                defaultDate: moment('2018-04-01'),
                columnFormat: 'ddd',
            },
            eventsA: [],
            eventsB: [],
            eventsC: [],
        };
    }

    componentWillReceiveProps(nextProps) {

        if (JSON.stringify(this.props) === JSON.stringify(nextProps)) {
            return false;
        }
        this.props = nextProps;

        const colors = ['#FF8A80', '#FF80AB', '#EA80FC', '#B388FF', '#8C9EFF', '#82B1FF', '#80D8FF', '#84FFFF',
            '#A7FFEB', '#B9F6CA', '#CCFF90', '#F4FF81', '#FFFF8D', '#FFE57F', '#FFD180', '#FF9E80',
            '#D7CCC8', '#F5F5F5', '#CFD8DC'];
        const color = colors[Math.floor(Math.random() * colors.length)];

        let day, time, event, updatedEvents;
        let determineDay = function (day) {
            if (day === "א") return '2018-04-01';
            else if (day === "ב") return '2018-04-02';
            else if (day === "ג") return '2018-04-03';
            else if (day === "ד") return '2018-04-04';
            else if (day === "ה") return '2018-04-05';
            else if (day === "ו") return '2018-04-06';
        };
        day = determineDay(this.props.courseObject[0].day);
        time = this.props.courseObject[0].hours.split(['-']);
        event = {
            title: `${this.props.courseObject[0].courseGroupId.courseNum}
                    ${this.props.courseObject[0].courseGroupId.groupLocation}`,
            start: `${day}T${time[1]}`,
            end: `${day}T${time[0]}`,
            color: color,

        };

        console.log(this.props.courseObject[0].courseGroupId.semesterName);

        // check the semester to decide if the event should be in semester A tab
        if(this.props.courseObject[0].courseGroupId.semesterName.indexOf('א') > -1) {
            updatedEvents = [...this.state.eventsA];
            updatedEvents.push(event);
            console.log(this.state.eventsA);
            this.setState({eventsA: updatedEvents});
        }
        // check the semester to decide if the event should be in semester B tab
        else if(this.props.courseObject[0].courseGroupId.semesterName.indexOf('ב') > -1) {
            updatedEvents = [...this.state.eventsB];
            updatedEvents.push(event);
            this.setState({eventsB: updatedEvents});
        }
        // check the semester to decide if the event should be in semester C tab
        else if(this.props.courseObject[0].courseGroupId.semesterName.indexOf('ג') > -1) {
            updatedEvents = [...this.state.eventsC];
            updatedEvents.push(event);
            this.setState({eventsC: updatedEvents});
        }

        return true;
    }

    handleClick = (calEvent, activeTabIndex) => {
        let deleteItem = window.confirm("Are you sure you want to delete this course?");
        if (deleteItem) {
            // check in what tab we are in and then updates the events list in this tab without the deleted course
            if (activeTabIndex === '1') {
                let updatedEvents = [...this.state.eventsA];
                updatedEvents = updatedEvents.filter(event => event.title !== calEvent.title)
                console.log(updatedEvents);
                this.setState({eventsA: updatedEvents});
            }
            else if (activeTabIndex === '2') {
                let updatedEvents = [...this.state.eventsB];
                updatedEvents = updatedEvents.filter(event => event.title !== calEvent.title)
                console.log(updatedEvents);
                this.setState({eventsB: updatedEvents});
            }
            else if (activeTabIndex === '3') {
                let updatedEvents = [...this.state.eventsC];
                updatedEvents = updatedEvents.filter(event => event.title !== calEvent.title)
                console.log(updatedEvents);
                this.setState({eventsC: updatedEvents});
            }
        }
    };

    render() {

        let {eventsA, eventsB, eventsC, options} = this.state;
        let semester;
        // check in which semester are we now and use the appropriate events list
        if (this.props.activeTabIndex === '1') semester = eventsA;
        else if (this.props.activeTabIndex === '2') semester = eventsB;
        else if (this.props.activeTabIndex === '3') semester = eventsC;

        return (
            <FullCalendar
                {...options}
                events={semester}
                eventClick={(calEvent) => this.handleClick(calEvent, this.props.activeTabIndex)}
            />
        )
    }
}

export default CalendarComponent;