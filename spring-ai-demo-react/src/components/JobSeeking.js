import React, { useState } from "react";

function JobSeeking(){
    const [jobTitle, setJobTitle] = useState('');
    const [industry, setIndustry] = useState('');
    const [location, setLocation] = useState('');
    const [employmentType, setEmploymentType] = useState('');
    const [job, setJob] = useState('');

    const createJob = async () => {
        try {
            const response = await fetch(`http://localhost:8080/searching-job?jobTitle=${jobTitle}&industry=${industry}&location=${location}&employmentType=${employmentType}`)
            const data = await response.text();
            console.log(data);
            setJob(data);
        } catch (error) {
            console.error("Error generating job : ", error)
        }
    };


    return(
        <div>
            <h2>search a job</h2>
            <input
                type="text"
                value={jobTitle}
                onChange={(e) => setJobTitle(e.target.value)}
                placeholder="Enter job Title"
            />
            <input
                type="text"
                value={industry}
                onChange={(e) => setIndustry(e.target.value)}
                placeholder="Enter industry"
            />
            <input
                type="text"
                value={location}
                onChange={(e) => setLocation(e.target.value)}
                placeholder="Enter the location"
            />
            <input
                type="text"
                value={employmentType}
                onChange={(e) => setEmploymentType(e.target.value)}
                placeholder="Enter the employmentType (e.g., full-time, part-time, remote)"
            />

            <button onClick={createJob}>search Job</button>
            <div className="output">
                    <pre className="job-text">{job}</pre>
            </div>

        </div>
    );


}

export default JobSeeking;

