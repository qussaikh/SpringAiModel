import React, { useState } from "react";

function TravelGenerator(){
    const [destination, setDestination] = useState('');
    const [interests, setInterests] = useState('');
    const [days, setDays] = useState('');
    const [budget, setBudget] = useState('');
    const [travel, setTravel] = useState('');

    const createTravel = async () => {
        try {
            const response = await fetch(`http://localhost:8080/travel-creator?destination=${destination}&interests=${interests}&days=${days}&budget=${budget}`)
            const data = await response.text();
            console.log(data);
            setTravel(data);
        } catch (error) {
            console.error("Error generating Travel : ", error)
        }
    };


    return(
        <div>
            <h2>Travel Itinerary Planner</h2>
            <input
                type="text"
                value={destination}
                onChange={(e) => setDestination(e.target.value)}
                placeholder="Enter destination"
            />
            <input
                type="text"
                value={interests}
                onChange={(e) => setInterests(e.target.value)}
                placeholder="Enter interests (comma seperated)"
            />
            <input
                type="text"
                value={days}
                onChange={(e) => setDays(e.target.value)}
                placeholder="Enter the duration"
            />
            <input
                type="text"
                value={budget}
                onChange={(e) => setBudget(e.target.value)}
                placeholder="Enter the budget"
            />

            <button onClick={createTravel}>Generate Travel</button>
            <div className="output">
                    <pre className="travel-text">{travel}</pre>
            </div>

        </div>
    );


}

export default TravelGenerator;

