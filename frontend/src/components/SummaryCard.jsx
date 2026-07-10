import { CCard, CCardBody } from '@coreui/react'
import CIcon from '@coreui/icons-react'

const SummaryCard = ({ title, value, subtitle, icon, color = 'primary' }) => {
  return (
    <CCard className="summary-card h-100">
      <CCardBody className="summary-card-body">
        {icon && (
          <span className={`summary-card-icon summary-card-icon-${color}`}>
            <CIcon icon={icon} height={28} />
          </span>
        )}

        <div className="summary-card-content">
          <div className="summary-card-title">{title}</div>
          <div className="summary-card-value">{value}</div>
          {subtitle && <div className="summary-card-subtitle">{subtitle}</div>}
        </div>
      </CCardBody>
    </CCard>
  )
}

export default SummaryCard
