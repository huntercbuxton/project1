
function PageHeader(props) {
    return (
        <header id='page-header'>
            <h1 className='page-header'>{ props.title }</h1>
            <p className='page-header'> { props.subtitle } </p>
        </header>
    );
}

export default PageHeader;