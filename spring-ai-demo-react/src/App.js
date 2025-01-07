import React, { useState } from 'react';
import './App.css';
import ImageGenerator from './components/ImageGenerator';
import ChatComponent from './components/ChatComponent';
import RecipeGenerator from './components/RecipeGenerator';
import TravelGenerator from './components/TravelGenerator';
import JobSeeking from './components/JobSeeking';

function App() {
  const [activeTab, setActiveTab] = useState('');

  const handleTabChange = (tab) => {
    //alert(tab)
    setActiveTab(tab);
  };

  return (
    <div className="App">
      <h2>Qussai AI Model</h2>
      <button  className={activeTab === 'chat' ? 'active' : ''}
      onClick={() => handleTabChange('chat')}>
        Ask AI
        </button>
      <button className={activeTab === 'image-generator' ? 'active' : ''}
       onClick={() => handleTabChange('image-generator')}>
        Image Generator
        </button>
      <button className={activeTab === 'recipe-generator' ? 'active' : ''}
      onClick={() => handleTabChange('recipe-generator')}>
        Recipe Generator
        </button>
        <button className={activeTab === 'travel-generator' ? 'active' : ''}
      onClick={() => handleTabChange('travel-generator')}>
        Travel Generator
        </button>
        <button className={activeTab === 'job-seeking' ? 'active' : ''}
      onClick={() => handleTabChange('job-seeking')}>
        search Job
        </button>

        <div>
          {activeTab === 'image-generator' && <ImageGenerator/>}
          {activeTab === 'chat' && <ChatComponent/>}
          {activeTab === 'recipe-generator' && <RecipeGenerator/>}
          {activeTab === 'travel-generator' && <TravelGenerator/>}
          {activeTab === 'job-seeking' && <JobSeeking/>}
        </div>
    </div>
  );
}

export default App;
