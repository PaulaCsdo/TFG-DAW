export default function AppContainer ( {children} ) {
  return (
    <div className='main-AppContainer'>
      <div className='AppContainer'>
        {children}
      </div>
    </div>
  )
  }