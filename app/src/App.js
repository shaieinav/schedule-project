import React, { Component } from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom';
import './App.css';
import Home from './containers/Home';
import CourseList from './containers/CourseList';
import CourseEdit from './containers/CourseEdit';

class App extends Component {

    render() {
        return (
            <Router>
                <Switch>
                    <Route path='/' exact={true} component={Home}/>
                    <Route path='/courses' exact={true} component={CourseList}/>
                    <Route path='/courses/:courseNum' component={CourseEdit}/>
                </Switch>
            </Router>
        )
    }
}

export default App;